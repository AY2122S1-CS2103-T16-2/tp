package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.OperationException;

/**
 * Re-executes the last command that was reversed
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    private static final String MESSAGE_REDO_SUCCESS = "Redo successful\nRemaining undo count: %d";
    private static final String MESSAGE_REDO_FAILURE = "No commands to redo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            int remaining = model.redo();
            return new CommandResult(String.format(MESSAGE_REDO_SUCCESS, remaining));
        } catch (OperationException e) {
            throw new CommandException(MESSAGE_REDO_FAILURE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RedoCommand;
    }
}
