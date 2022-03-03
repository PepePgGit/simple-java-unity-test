package repository;

import entity.Mage;
import lombok.Getter;

import java.util.*;

@Getter
public class MageRepository
{
    private Collection<Mage> collection;

    public MageRepository()
    {
        this.collection = new ArrayList<>();
    }

    public Optional<Mage> find(String name)
    {
        Mage mageFound = null;

        for(Mage mag : collection)
        {
            if(mag.getName() == name)
            {
                mageFound = mag;
                break;
            }
        }

        return Optional.ofNullable(mageFound);
    }

    public void delete(String name)
    {
        if(this.find(name).isEmpty())
        {
            throw new IllegalArgumentException("cannot delete not present in collection");
        }
        collection.remove(this.find(name).get());
    }

    public void save(Mage mage)
    {
        if(this.find(mage.getName()).isPresent())
        {
            throw new IllegalArgumentException("cannot save already present in collection");
        }
        collection.add(mage);
    }
}
