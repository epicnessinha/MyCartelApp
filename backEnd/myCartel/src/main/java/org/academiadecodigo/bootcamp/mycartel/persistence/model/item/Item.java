package org.academiadecodigo.bootcamp.mycartel.persistence.model.item;

import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.AbstractModel;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
public abstract class Item extends AbstractModel {

    private String itemName;
    private String description;
    @ManyToOne
    private GangMember gangMember;



    /**
     * Gets the account costumer
     *
     * @return the customer
     */
    public GangMember getGangMember() {
        return gangMember;
    }

    /**
     * Sets the account costumer
     *
     * @param gangMember the customer to set
     */
    public void setGangMember(GangMember gangMember) {
        this.gangMember = gangMember;
    }

    /**
     * Gets the account type
     *
     * @return the account type
     */
    public abstract ItemType getItemType();

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", gangMember=" + gangMember +
                '}';
    }
}
