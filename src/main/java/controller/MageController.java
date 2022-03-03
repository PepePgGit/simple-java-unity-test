package controller;

import entity.Mage;
import lombok.Getter;
import repository.MageRepository;

@Getter
public class MageController
{
    private MageRepository repository;

    public MageController(MageRepository repository)
    {
        this.repository = repository;
    }

    public String find(String name)
    {
        String retString;

        if(repository.find(name).isEmpty())
        {
            retString = "not found";
        }
        else
        {
            retString = repository.find(name).toString();
        }

        return retString;
    }

    public String delete(String name)
    {
        String retString = "done";
        try
        {
            repository.delete(name);
        } catch (IllegalArgumentException e)
        {
            retString = "not found";
        }

        return retString;
    }

    public String save(String name, String level)
    {
        String retString = "done";
        try
        {
            repository.save(new Mage(name, Integer.parseInt(level)));
        } catch (IllegalArgumentException e)
        {
            retString = "bad request";
        }

        return retString;
    }
}
