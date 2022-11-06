package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private ClinicService clinicService;

    @Mock
    private Map<String, Object> model;

    @InjectMocks
    private VetController controller;
    List<Vet> vetList = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vetList.add(new Vet());
        given(clinicService.findVets()).willReturn(vetList);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void showVetList1() {
        // when
        String view = controller.showVetList(model);
        // then
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any());
        assertThat("vets/vetList").isEqualToIgnoringCase(view);


    }

    @Test
    void showResourcesVetList1() {
        // when
        Vets vets = controller.showResourcesVetList();

        // then
        then(clinicService.findVets());
        assertThat(vets.getVetList()).hasSize(1);
    }

    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));


    }


    @Test
    void showVetList() {

        // when
        String view = controller.showVetList(model);

       // then
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any());
        assertThat("vets/vetList").isEqualToIgnoringCase(view);
    }

    @Test
    void showResourcesVetList() {

        // when
        Vets vets = controller.showResourcesVetList();

        // then
        then(clinicService).should().findVets();
        assertThat(vets.getVetList()).hasSize(1);
    }
}