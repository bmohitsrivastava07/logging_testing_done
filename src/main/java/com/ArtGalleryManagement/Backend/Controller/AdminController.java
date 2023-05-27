package com.ArtGalleryManagement.Backend.Controller;

import com.ArtGalleryManagement.Backend.GlobalExceptionsHandler.MethodNotAllowedException;
import com.ArtGalleryManagement.Backend.RequestModels.*;
import com.ArtGalleryManagement.Backend.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import com.ArtGalleryManagement.Backend.utils.*;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger=LoggerFactory.getLogger(AdminController.class);
    private AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/secure/increase/product/quantity")
    public void increaseProductQuantity(@RequestParam Long productId) throws Exception {
        String admin = "admin";
        if (admin == null || !admin.equals("admin")) {
        	logger.warn("Unauthorized access to increaseProductQuantity endpoint");
            throw new MethodNotAllowedException();
        }
        logger.info("Increasing product quantity for productId: {}",productId);
        adminService.increaseProductQuantity(productId);
    }

    @PutMapping("/secure/decrease/product/quantity")
    public void decreaseProductQuantity(@RequestParam Long productId) throws Exception {
        String admin ="admin";
        if (admin == null || !admin.equals("admin")) {
        	logger.warn("Unauthorized access to decreaseProductQuantity endpoint");
            throw new MethodNotAllowedException();
        }
        logger.info("Decreasing product quantity for productId: {}",productId);
        adminService.decreaseProductQuantity(productId);
    }

    @PostMapping("/secure/add/product")
    public void postProduct(@RequestBody AddProductRequest addProductRequest) throws Exception {
        String admin = "admin";
        if (admin == null || !admin.equals("admin")) {
        	logger.warn("Unauthorized access to postProduct endpoint");
            throw new MethodNotAllowedException();
        }
        logger.info("Adding a new product: {}",addProductRequest);
        adminService.postProduct(addProductRequest);
    }

    @DeleteMapping("/secure/delete/product")
    public void deleteProduct(@RequestParam Long productId) throws Exception {
        String admin = "admin";
        if (admin == null || !admin.equals("admin")) {
        	logger.warn("Unauthorized access to deleteProduct endpoint");
            throw new MethodNotAllowedException();
        }
        logger.info("Deleting product with productId: {}",productId);
        adminService.deleteProduct(productId);
    }

}












