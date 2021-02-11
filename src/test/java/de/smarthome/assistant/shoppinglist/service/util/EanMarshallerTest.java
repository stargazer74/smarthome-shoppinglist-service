/*
 * Copyright (c) 2019. Chris Wohlbrecht
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.smarthome.assistant.shoppinglist.service.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.smarthome.assistant.shoppinglist.TestBase;
import de.smarthome.assistant.shoppinglist.service.dto.EanRequestDTO;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EanMarshallerTest extends TestBase {

    private String successResponse;

    private String failedResponse;

    @BeforeEach
    public void setUp() {
        successResponse = "error=0\n" + "---\n" + "asin=\n" + "name=Gesichtscreme\n" + "detailname=Nachtcreme mit Gelee-Royale und Honig\n"
                + "vendor=Bienen Di�tic GmbH\n" + "maincat=Kosmetische Mittel\n" + "subcat=Gesichtspflege\n" + "maincatnum=15\n"
                + "subcatnum=1\n" + "contents=0\n" + "pack=257\n" + "origin=Deutschland\n"
                + "descr=Gelee Royale und Honig, diese Kostbarkeiten aus der Natur mit Vitaminen, Mineralien, Enzymen und Feuchthaltefaktoren machen die Haut geschmeidig und zusammen mit den Vitaminen A + E bauen sie die Haut intensiv auf und staerken sie. Besonders geeignet fuer trockene und empfindliche Haut. - Wohltuende Pflanzenoele und Aloe Vera versorgen die Haut mit Feuchtigkeit, schuetzen und straffen sie. Die Zellteilung wird gefoerdert und die Elastizitaet der Haut wird gesteigert. Zutaten: Gelee Royale, Honig, Bienenwachs, Avocadooel, Jojobaoel, Suessmandeloel, Olivenoel, Aloe Vera und die Vitamine A und E. 50 ml-Dose\n"
                + "name_en=\n" + "detailname_en=\n" + "descr_en=\n" + "validated=0 %\n" + "---";
        failedResponse = "error=1\n" + "---\n" + "asin=\n" + "name=Gesichtscreme\n" + "detailname=Nachtcreme mit Gelee-Royale und Honig\n"
                + "vendor=Bienen Di�tic GmbH\n" + "maincat=Kosmetische Mittel\n" + "subcat=Gesichtspflege\n" + "maincatnum=15\n"
                + "subcatnum=1\n" + "contents=0\n" + "pack=257\n" + "origin=Deutschland\n"
                + "descr=Gelee Royale und Honig, diese Kostbarkeiten aus der Natur mit Vitaminen, Mineralien, Enzymen und Feuchthaltefaktoren machen die Haut geschmeidig und zusammen mit den Vitaminen A + E bauen sie die Haut intensiv auf und staerken sie. Besonders geeignet fuer trockene und empfindliche Haut. - Wohltuende Pflanzenoele und Aloe Vera versorgen die Haut mit Feuchtigkeit, schuetzen und straffen sie. Die Zellteilung wird gefoerdert und die Elastizitaet der Haut wird gesteigert. Zutaten: Gelee Royale, Honig, Bienenwachs, Avocadooel, Jojobaoel, Suessmandeloel, Olivenoel, Aloe Vera und die Vitamine A und E. 50 ml-Dose\n"
                + "name_en=\n" + "detailname_en=\n" + "descr_en=\n" + "validated=0 %\n" + "---";
    }

    @Test
    public void unmarshallSuccessTest() {
        final EanMarshaller eanMarshaller = new EanMarshaller();
        final Optional<EanRequestDTO> eanRequestDTO = eanMarshaller.unmarshall(this.successResponse);

        assertTrue(eanRequestDTO.isPresent());
        assertThat(eanRequestDTO.get().getSubCategory(), equalTo("Gesichtspflege"));
        assertThat(eanRequestDTO.get().getProductName(), equalTo("Gesichtscreme"));
        assertThat(eanRequestDTO.get().getCategory(), equalTo("Kosmetische Mittel"));
    }

    @Test
    public void unmarshallFailedTest() {
        final EanMarshaller eanMarshaller = new EanMarshaller();
        final Optional<EanRequestDTO> eanRequestDTO = eanMarshaller.unmarshall(this.failedResponse);

        assertFalse(eanRequestDTO.isPresent());

    }
}
