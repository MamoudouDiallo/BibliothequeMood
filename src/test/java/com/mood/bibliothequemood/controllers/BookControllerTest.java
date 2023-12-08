package com.mood.bibliothequemood.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author amadou98 <amadoubhoyediallo98@gmail.com>
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("BookControllerTest")
@DisplayName("Unit testing book controller endpoint")
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    private JSONObject json;

    @BeforeAll
    @AfterAll
    public void clearDatabase() {
        this.bookController.deleteAll();
        json = null;
    }

    @Test
    @Order(value = 1)
    @DisplayName("Create book")
    public void testThatWeCanCreateBook() throws Exception {
        String requestPayload = "{"
                + "\"title\": \"Kirikou et la sorcière 1\","
                + "\"image\": \"https://www1.hds-streaming.to/poster/kirikou-et-la-sorciegrave.jpg\","
                + "\"description\": \"Le minuscule Kirikou nait dans un village d'Afrique sur lequel une sorciere\","
                + "\"author\": \"Theo Sebeko\","
                + "\"library\": \"Afrique\","
                + "\"pageNumber\": \"50\","
                + "\"language\": \"FRENCH\""
                + "}";
        MvcResult mockMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rest/books")
                        .content(requestPayload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Kirikou et la sorcière 1"))
                .andExpect(jsonPath("$.image").value("https://www1.hds-streaming.to/poster/kirikou-et-la-sorciegrave.jpg"))
                .andExpect(jsonPath("$.description").value("Le minuscule Kirikou nait dans un village d'Afrique sur lequel une sorciere"))
                .andExpect(jsonPath("$.author").value("Theo Sebeko"))
                .andExpect(jsonPath("$.library").value("Afrique"))
                .andExpect(jsonPath("$.pageNumber").value("50"))
                .andExpect(jsonPath("$.language").value("FRENCH"))
                .andReturn();

        json = new JSONObject(mockMvcResult.getResponse().getContentAsString());

    }

    @Test
    @Order(value = 2)
    @DisplayName("Get or Read a book with ID")
    public void testGetBook() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rest/books/" + json.getInt("id"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Kirikou et la sorcière 1"))
                .andExpect(jsonPath("$.image").value("https://www1.hds-streaming.to/poster/kirikou-et-la-sorciegrave.jpg"))
                .andExpect(jsonPath("$.description").value("Le minuscule Kirikou nait dans un village d'Afrique sur lequel une sorciere"))
                .andExpect(jsonPath("$.author").value("Theo Sebeko"))
                .andExpect(jsonPath("$.library").value("Afrique"))
                .andExpect(jsonPath("$.pageNumber").value("50"))
                .andExpect(jsonPath("$.language").value("FRENCH"));
    }

    @Test
    @Order(value = 3)
    @DisplayName("Show books list")
    public void testGetBooks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rest/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Kirikou et la sorcière 1"))
                .andExpect(jsonPath("$[0].image").value("https://www1.hds-streaming.to/poster/kirikou-et-la-sorciegrave.jpg"))
                .andExpect(jsonPath("$[0].description").value("Le minuscule Kirikou nait dans un village d'Afrique sur lequel une sorciere"))
                .andExpect(jsonPath("$[0].author").value("Theo Sebeko"))
                .andExpect(jsonPath("$[0].library").value("Afrique"))
                .andExpect(jsonPath("$[0].pageNumber").value("50"))
                .andExpect(jsonPath("$[0].language").value("FRENCH"));
    }

    @Test
    @Order(value = 4)
    @DisplayName("Update a book with ID")
    public void testUpdateBook() throws Exception {
        String requestPayload = "{"
                + "\"id\":" + json.getInt("id") + ","
                + "\"title\": \"Avatar La Voie de l'eau\","
                + "\"image\": \"https://image.tmdb.org/t/p/w300/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg\","
                + "\"description\": \"Une dizaine d'années se sont écoulés depuis les précédents\","
                + "\"author\": \"James Cameron\","
                + "\"library\": \"Pandora\","
                + "\"pageNumber\": \"50\","
                + "\"language\": \"ENGLISH\""
                + "}";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/rest/books/" + json.getInt("id"))
                        .content(requestPayload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(json.getInt("id")))
                .andExpect(jsonPath("$.title").value("Avatar La Voie de l'eau"))
                .andExpect(jsonPath("$.image").value("https://image.tmdb.org/t/p/w300/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg"))
                .andExpect(jsonPath("$.description").value("Une dizaine d'années se sont écoulés depuis les précédents"))
                .andExpect(jsonPath("$.author").value("James Cameron"))
                .andExpect(jsonPath("$.library").value("Pandora"))
                .andExpect(jsonPath("$.pageNumber").value("50"))
                .andExpect(jsonPath("$.language").value("ENGLISH"));
    }

    @Test
    @Order(value = 5)
    @DisplayName("Delete a student with ID")
    public void testDeleteBook() throws Exception {
        // Envoyer une requête DELETE pour supprimer le livre
           this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/rest/books/" + json.getInt("id"))
                           .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        // Vérifier que le livre a été supprimé
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rest/books/" + json.getInt("id"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(value = 6)
    @DisplayName("Wrong book ID")
    public void testThatWeCanNotFoundBook   () throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rest/books/" + json.getInt("id")))
                .andDo(print())
                .andExpect(status().isNotFound());
                //.andExpect(jsonPath("$.status").value(404))
                //.andExpect(jsonPath("$.errorMessage").value("Book not found with id: " + json.getInt("id")));
    }

}
