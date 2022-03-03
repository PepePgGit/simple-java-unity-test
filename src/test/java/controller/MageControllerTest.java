package controller;

import entity.Mage;
import org.junit.Test;
import repository.MageRepository;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MageControllerTest
{
    @Test
    public void find_ObjectExist_true()
    {
        String retString;
        Mage mageTest = new Mage("test", 2);

        MageRepository mageRepo = mock(MageRepository.class);
        when(mageRepo.find(mageTest.getName())).thenReturn(Optional.ofNullable(mageTest));

        MageController mageControl = new MageController(mageRepo);


        mageControl.getRepository().getCollection().add(mageTest);

        retString = mageControl.find(mageTest.getName());

        assert retString.equals(Optional.ofNullable(mageTest).toString()) : "Failed to find existing object";
    }

    @Test
    public void find_ObjectExist_false()
    {
        String retString;

        MageRepository mageRepo = mock(MageRepository.class);
        when(mageRepo.find("test")).thenReturn(Optional.ofNullable(null));

        MageController mageControl = new MageController(mageRepo);

        retString = mageControl.find("test");

        assert retString.equals("not found") : "Failed to find non-existing object";
    }

    @Test
    public void delete_ObjectExist_true()
    {
        String retString;

        MageRepository mageRepo = mock(MageRepository.class);
        doNothing().when(mageRepo).delete("test");

        MageController mageControl = new MageController(mageRepo);

        retString = mageControl.delete("test");

        assert retString.equals("done") : "Failed to delete existing object";
    }

    @Test
    public void delete_ObjectExist_false()
    {
        String retString;

        MageRepository mageRepo = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(mageRepo).delete("test");

        MageController mageControl = new MageController(mageRepo);
        retString = mageControl.delete("test");

        assert retString.equals("not found") : "Failed to delete non-existing object";
    }

    @Test
    public void save_ObjectExist_true()
    {
        String retString;
        Mage mageTest = new Mage("test", 2);

        MageRepository mageRepo = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(mageRepo).save(mageTest);

        MageController mageControl = new MageController(mageRepo);
        retString = mageControl.save(mageTest.getName(), Integer.toString(mageTest.getLevel()));

        assert retString.equals("bad request") : "Failed to save already existing object";
    }

    @Test
    public void save_ObjectExist_false()
    {
        String retString;
        Mage mageTest = new Mage("test", 2);

        MageRepository mageRepo = mock(MageRepository.class);
        doNothing().when(mageRepo).save(mageTest);

        MageController mageControl = new MageController(mageRepo);

        retString = mageControl.save(mageTest.getName(), Integer.toString(mageTest.getLevel()));

        assert retString.equals("done") : "Failed to save non-existing object";
    }
}
