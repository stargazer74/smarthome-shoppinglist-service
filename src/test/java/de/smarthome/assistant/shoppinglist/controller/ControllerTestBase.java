package de.smarthome.assistant.shoppinglist.controller;

import de.smarthome.assistant.shoppinglist.TestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
abstract class ControllerTestBase extends TestBase {

    @Autowired
    protected MockMvc mockMvc;

}
