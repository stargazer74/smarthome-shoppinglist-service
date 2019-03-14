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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class EanMarshaller {

    public EanRequestDTO unmarshall(String requestResult){
        return null;
    }

    private Map<String, String> generateMap(final String response)  {
        final String[] lines = response.split("\\r?\\n");

        final String[] url = lines[0].split("=", 2);
        final String[] pk = lines[1].split("=");
        final String[] a_id = lines[2].split("=");


        HashMap<String, String> returnMap = new HashMap<>();
        returnMap.put(url[0], url[1]);
        returnMap.put(pk[0], pk[1]);
        returnMap.put(a_id[0], a_id[1]);

        if (!returnMap.containsKey("url") || !returnMap.containsKey("pk") || !returnMap.containsKey("a_id"))
;

        return returnMap;
    }
}