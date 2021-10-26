package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PartialKeyContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.TutorialIdOrRoleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public static final String TUTORIAL_ID_TYPE = "T/";
    public static final String ROLE_TYPE = "r/";
    public static final String TAG_TYPE = "t/";

    /**
     * Parses the input.
     * @param args input from user
     * @return new FindCommand
     * @throws ParseException
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !trimmedArgs.contains(" ")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] typeKeywords = trimmedArgs.split("\\s+");
        //check that there is a key inputted for the command after the attribute type
        assert(typeKeywords.length > 1);
        //check that the attribute type is inputted as a 2-character string, e.g. "n/" and not "n/alex"
        //assert(typeKeywords[0].length() == 2);
        String type = typeKeywords[0];
        List<String> keywords = Arrays.asList(typeKeywords);

        if (type.equals(TUTORIAL_ID_TYPE) || type.equals(ROLE_TYPE)) {
            return new FindCommand(new TutorialIdOrRoleContainsKeywordsPredicate(keywords, type));
        } else if (type.equals(TAG_TYPE)) {
            return new FindCommand(new TagContainsKeywordsPredicate(keywords, type));
        }
        //search partial keys for the other types
        return new FindCommand(new PartialKeyContainsKeywordsPredicate(keywords, type));
    }
}
