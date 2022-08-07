package org.academiadecodigo.bootcamp.mycartel.persistence.dao.jpa;

import org.academiadecodigo.bootcamp.mycartel.persistence.dao.GangMemberDao;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.springframework.stereotype.Repository;

@Repository
public class JpaGangMemberDao extends GenericJpaDao<GangMember> implements GangMemberDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaGangMemberDao() {
        super(GangMember.class);
    }
}
