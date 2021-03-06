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

package de.smarthome.assistant.shoppinglist.controller;

import de.smarthome.assistant.shoppinglist.component.Product.ProductI;
import de.smarthome.assistant.shoppinglist.controller.dto.ProductRequestDTO;
import de.smarthome.assistant.shoppinglist.controller.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductI product;

    @Autowired
    public ProductController(ProductI product) {
        this.product = product;
    }

    /**
     * If the product is found, it returns a response entity with an ProductResponseDTO an status code 200.
     * Else it returns a status code 404.
     *
     * @param ean number
     * @return ResponseEntity<ProductResponseDTO>
     */
    @RequestMapping(value = "/{ean}", method = RequestMethod.GET)
    public ResponseEntity<ProductResponseDTO> get(@PathVariable("ean") String ean) {
        return product.getProduct(ean).map(productResponseDTO -> ResponseEntity.ok().body(productResponseDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Insert a new product into database.
     *
     * @param productRequestDTO a ProductRequestDTO
     * @return the ResponseEntity<ProductResponseDTO> of the insert object or status 404 if insert failed
     */
    @RequestMapping(value = "/insert", method = RequestMethod.PUT)
    public ResponseEntity<ProductResponseDTO> insert(ProductRequestDTO productRequestDTO) {
        return product.addProduct(productRequestDTO).map(productResponseDTO -> ResponseEntity.ok().body(productResponseDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete a product.
     *
     * @param id the product id to delete
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        product.deleteProduct(id);
    }

    /**
     * Return all products
     *
     * @return List<ProductResponseDTO> a list of all products
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProductResponseDTO> list(){
        return product.listProducts();
    }
}
