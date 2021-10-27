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

class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_afterDelete_success() throws CommandException {
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromOneBased(1));
        deleteCommand.execute(model);

        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDO_SUCCESS, 0);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_asFirstCommand_failure() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_UNDO_FAILURE;
        assertCommandFailure(undoCommand, model, expectedMessage);
    }

    @Test
    void testEquals() {
        UndoCommand undoFirstCommand = new UndoCommand();
        UndoCommand undoSecondCommand = new UndoCommand();

        RedoCommand redoCommand = new RedoCommand();

        assertTrue(undoFirstCommand.equals(undoSecondCommand));
        assertFalse(undoFirstCommand.equals(redoCommand));
    }
}
