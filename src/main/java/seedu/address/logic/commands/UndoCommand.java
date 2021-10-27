package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.OperationException;

/**
 * Reverses the last command that modified data
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_SUCCESS = "Undo successful\nRemaining undo count: %d";
    public static final String MESSAGE_UNDO_FAILURE = "No commands to undo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            int remaining = model.undo();
            return new CommandResult(String.format(MESSAGE_UNDO_SUCCESS, remaining));
        } catch (OperationException e) {
            throw new CommandException(MESSAGE_UNDO_FAILURE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UndoCommand;
    }
}
