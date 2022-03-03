package repository;

import entity.Mage;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MageRepositoryTest
{
    @Test
    public void find_ObjectExist_true()
    {
        MageRepository mageRepo = new MageRepository();
        Mage mageTest = new Mage("mage1", 5);

        mageRepo.getCollection().add(mageTest);

        String stringTested = mageRepo.find(mageTest.getName()).toString();
        String stringGood = Optional.ofNullable(mageTest).toString();

        assert stringTested.equals(stringGood) : "Failed to find object";
    }

    @Test
    public void find_ObjectExist_false()
    {
        MageRepository mageRepo = new MageRepository();

        assert mageRepo.find("anything").isEmpty() : "Found object that doesn't exist";
    }

    @Test
    public void delete_ObjectExist_false()
    {
        MageRepository mageRepo = new MageRepository();

        assertThatIllegalArgumentException().isThrownBy(() ->
        {
            mageRepo.delete("anything");
        });
    }

    @Test
    public void save_ObjectExist_true()
    {
        MageRepository mageRepo = new MageRepository();

        mageRepo.getCollection().add(new Mage("mage1", 5));

        assertThatIllegalArgumentException().isThrownBy(() ->
        {
            mageRepo.save(new Mage("mage1", 5));
        });
    }
}
