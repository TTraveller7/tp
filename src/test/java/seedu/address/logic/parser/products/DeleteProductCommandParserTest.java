package seedu.address.logic.parser.products;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.products.DeleteProductCommand;

public class DeleteProductCommandParserTest {

    private DeleteProductCommandParser parser = new DeleteProductCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteProductCommand(INDEX_FIRST_PRODUCT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteProductCommand.MESSAGE_USAGE));
    }
}
