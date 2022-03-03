package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Mage
{
    @Id
    private String name;

    private int level;

    public Mage(String name, int lvl)
    {
        this.name = name;
        this.level = lvl;
    }

    @Override
    public String toString()
    {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
