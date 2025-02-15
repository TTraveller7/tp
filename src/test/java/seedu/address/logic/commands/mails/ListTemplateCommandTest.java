package seedu.address.logic.commands.mails;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTemplateAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.address.testutil.TypicalTemplates.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTemplateCommand.
 */
public class ListTemplateCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTemplateCommand(), model, ListTemplateCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);
        assertCommandSuccess(new ListTemplateCommand(), model, ListTemplateCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
