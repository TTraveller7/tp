package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.testutil.ContactBuilder;

public class UniqueContactListTest {

    private final UniqueContactList uniqueContactList = new UniqueContactList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueContactList.add(ALICE);
        assertTrue(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueContactList.add(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueContactList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueContactList.add(ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.setContact(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, ALICE);
        UniqueContactList expectedUniquePersonList = new UniqueContactList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniqueContactList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueContactList.add(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueContactList.setContact(ALICE, editedAlice);
        UniqueContactList expectedUniquePersonList = new UniqueContactList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniqueContactList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, BOB);
        UniqueContactList expectedUniquePersonList = new UniqueContactList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueContactList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueContactList.add(ALICE);
        uniqueContactList.add(BOB);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContact(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueContactList.add(ALICE);
        uniqueContactList.remove(ALICE);
        UniqueContactList expectedUniquePersonList = new UniqueContactList();
        assertEquals(expectedUniquePersonList, uniqueContactList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((UniqueContactList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueContactList.add(ALICE);
        UniqueContactList expectedUniquePersonList = new UniqueContactList();
        expectedUniquePersonList.add(BOB);
        uniqueContactList.setContacts(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniqueContactList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((List<Contact>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueContactList.add(ALICE);
        List<Contact> personList = Collections.singletonList(BOB);
        uniqueContactList.setContacts(personList);
        UniqueContactList expectedUniquePersonList = new UniqueContactList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueContactList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Contact> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContacts(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueContactList.asUnmodifiableObservableList().remove(0));
    }
}
