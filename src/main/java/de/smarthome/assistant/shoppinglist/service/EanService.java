
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

package de.smarthome.assistant.shoppinglist.service;

import de.smarthome.assistant.shoppinglist.service.dto.EanRequestDTO;
import de.smarthome.assistant.shoppinglist.service.util.EanMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@PropertySource({ "classpath:conf/barcodedb.properties" })
@Service
public class EanService implements EanServiceI {

    @Value("${barcodedb.url}")
    private String baseUrl;

    @Value("${barcodedb.user.id}")
    private String userId;

    private final EanMarshaller eanMarshaller;

    @Autowired
    public EanService(EanMarshaller eanMarshaller) {
        this.eanMarshaller = eanMarshaller;
    }

    /**
     * Gets product information for an ean number from a webservice.
     *
     * @param ean a ean number from a barcode
     * @return EanRequestDTO
     */
    @Override
    public EanRequestDTO getProductInformation(String ean) {
        final UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(baseUrl).queryParam("ean", ean)
                .queryParam("cmd", "query").queryParam("queryid", userId).build();
        RestTemplate restTemplate = new RestTemplate();
        String uriString = uriComponents.toUriString();
        String result = restTemplate.getForObject(uriString, String.class);
        return eanMarshaller.unmarshall(result);
    }
}
