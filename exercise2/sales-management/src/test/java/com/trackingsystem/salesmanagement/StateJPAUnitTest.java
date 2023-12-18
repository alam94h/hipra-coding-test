package com.trackingsystem.salesmanagement;

import com.trackingsystem.salesmanagement.model.State;
import com.trackingsystem.salesmanagement.repository.RegionRepository;
import com.trackingsystem.salesmanagement.repository.StateRepository;
import com.trackingsystem.salesmanagement.model.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StateJPAUnitTest {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    RegionRepository regionRepository;

    @Test
    public void should_store_a_state() {
        Region region = regionRepository.findById(1L).get();
        State state = stateRepository.save(new State("Catalunya", region));
        assertThat(state).hasFieldOrPropertyWithValue("name", "Catalunya");
    }

    @Test
    public void should_find_state_by_id() {
        Region region = regionRepository.findById(1L).get();
        State state = stateRepository.save(new State("Catalunya", region));

        State foundState = stateRepository.findById(state.getId()).get();

        assertThat(foundState).isEqualTo(state);
    }

    @Test
    public void should_update_State_by_id() {
        Region region = regionRepository.findById(1L).get();
        State state = stateRepository.save(new State("Catalunya", region));

        State updatedState = new State("Andalusia", region);

        State State2 = stateRepository.findById(state.getId()).get();
        State2.setName(updatedState.getName());
        stateRepository.save(State2);

        State checkSale = stateRepository.findById(state.getId()).get();

        assertThat(checkSale.getId()).isEqualTo(state.getId());
        assertThat(checkSale.getName()).isEqualTo(updatedState.getName());
    }
}