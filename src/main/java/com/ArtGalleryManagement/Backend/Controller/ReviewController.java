package com.ArtGalleryManagement.Backend.Controller;

import com.ArtGalleryManagement.Backend.GlobalExceptionsHandler.UserEmailNotFoundException;
import com.ArtGalleryManagement.Backend.RequestModels.*;
import com.ArtGalleryManagement.Backend.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import com.ArtGalleryManagement.Backend.utils.*;*/
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private static final Logger logger=LoggerFactory.getLogger(ReviewController.class);
	private ReviewServiceImpl reviewService;

	public ReviewController(ReviewServiceImpl reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/secure/user/product")
	public Boolean reviewProductByUser(@RequestParam Long productId) throws Exception {
		String userEmail = "arsh@gmail.com";

		if (userEmail == null) {
	         logger.error("User Email not found");
			throw new UserEmailNotFoundException();
		}
		logger.info("Checking if user {} reviewed product with id: {}",userEmail,productId);
		return reviewService.userReviewListed(userEmail, productId);
	}

	@PostMapping("/secure")
	public void postReview(@RequestBody ReviewRequest reviewRequest) throws Exception {
		String userEmail = "ankita@gmail.com";
		if (userEmail == null) {
			logger.error("User Email not found");
			throw new UserEmailNotFoundException();
		}
		logger.info("Posting review for user {}: {}",userEmail,reviewRequest);
		reviewService.postReview(userEmail, reviewRequest);
	}
}
