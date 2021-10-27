package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class RedoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_afterUndo_success() throws CommandException {
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromOneBased(1));
        deleteCommand.execute(model);

        UndoCommand undoCommand = new UndoCommand();
        undoCommand.execute(model);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        deleteCommand.execute(expectedModel);
        String expectedMessage = String.format(RedoCommand.MESSAGE_REDO_SUCCESS, 0);

        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_asFirstCommand_failure() {
        RedoCommand redoCommand = new RedoCommand();

        String expectedMessage = RedoCommand.MESSAGE_REDO_FAILURE;
        assertCommandFailure(redoCommand, model, expectedMessage);
    }

    @Test
    void testEquals() {
        RedoCommand redoFirstCommand = new RedoCommand();
        RedoCommand redoSecondCommand = new RedoCommand();

        UndoCommand undoCommand = new UndoCommand();

        assertTrue(redoFirstCommand.equals(redoSecondCommand));
        assertFalse(redoFirstCommand.equals(undoCommand));
    }
}
