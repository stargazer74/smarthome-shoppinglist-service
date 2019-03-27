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

import de.smarthome.assistant.shoppinglist.service.dto.EanRequestDTO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class EanMarshaller {

    /**
     * unmarshall the incoming text from topengtindb.org
     *
     * @param requestResult incoming response
     * @return an EanRequestDTO
     */
    public EanRequestDTO unmarshall(String requestResult){
        Map<String, String> stringStringMap = generateMap(requestResult);
        EanRequestDTO eanRequestDTO = new EanRequestDTO();
        eanRequestDTO.setProductName(stringStringMap.get("name"));
        eanRequestDTO.setCategory(stringStringMap.get("maincat"));
        eanRequestDTO.setSubCategory(stringStringMap.get("subcat"));
        return eanRequestDTO;
    }

    private Map<String, String> generateMap(final String response)  {
        final String[] lines = response.split("\\r?\\n");
        HashMap<String, String> returnMap = new HashMap<>();

        final String[] error = lines[0].split("=", 2);
        if (error[1]!="0"){
            final String[] name = lines[3].split("=");
            final String[] mainCategory = lines[6].split("=");
            final String[] subCategory = lines[7].split("=");

            returnMap.put(name[0], name[1]);
            returnMap.put(mainCategory[0], mainCategory[1]);
            returnMap.put(subCategory[0], subCategory[1]);
        }else{
            returnMap.put(error[0], error[1]);
        }
        return returnMap;
    }
}
