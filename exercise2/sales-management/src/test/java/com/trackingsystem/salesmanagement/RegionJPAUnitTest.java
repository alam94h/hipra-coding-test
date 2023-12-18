package com.trackingsystem.salesmanagement;

import static org.assertj.core.api.Assertions.assertThat;

import com.trackingsystem.salesmanagement.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.trackingsystem.salesmanagement.model.Region;

@DataJpaTest
public class RegionJPAUnitTest {

    @Autowired
    RegionRepository repository;

    @Test
    public void should_store_a_region() {
        // Arrange
        Region region = repository.save(new Region("Bèlgica"));

        // Assert
        assertThat(region).hasFieldOrPropertyWithValue("name", "Bèlgica");
    }

    @Test
    public void should_find_region_by_id() {
        // Arrange
        Region region = repository.save(new Region("Bèlgica"));

        // Act
        Region foundRegion = repository.findById(region.getId()).get();

        // Assert
        assertThat(foundRegion).isEqualTo(region);
    }

    @Test
    public void should_update_region_by_id() {
        // Arrange
        Region region1 = repository.save(new Region("Bèlgica"));

        // Act
        Region updatedRegion = new Region("Holanda");
        Region region2 = repository.findById(region1.getId()).get();
        region2.setName(updatedRegion.getName());
        repository.save(region2);

        // Assert
        Region checkRegion = repository.findById(region1.getId()).get();
        assertThat(checkRegion.getId()).isEqualTo(region1.getId());
        assertThat(checkRegion.getName()).isEqualTo(updatedRegion.getName());
    }

    @Test
    public void should_delete_region_by_id() {
        // Arrange
        Region region = repository.save(new Region("Bèlgica"));

        // Act
        repository.deleteById(region.getId());

        // Assert
        assertThat(repository.findById(region.getId())).isEmpty();
    }
}
