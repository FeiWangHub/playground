package com.stripe.sample;

import java.nio.file.Paths;

import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class Server {

  public static void main(String[] args) {
    port(4242);

    // This is your test secret API key.
    Stripe.apiKey = "sk_test_51KWe29JfHH6VZXI5zmeOnpgRZE6cfrRKLXg8cft5igcQJayL2WLQ3HMbOIgUWGF89N6X813i6HvSoRlv4FhQvu3x00fVKTiN8F";

    staticFiles.externalLocation(
        Paths.get("public").toAbsolutePath().toString());

    post("/create-checkout-session", (request, response) -> {
        String YOUR_DOMAIN = "http://39.100.78.62:4242";
        SessionCreateParams params =
          SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(YOUR_DOMAIN + "/success.html")
            .setCancelUrl(YOUR_DOMAIN + "/cancel.html")
            .addLineItem(
              SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
//                .setPrice("price_1KXTaBJfHH6VZXI53pYuVy9M")
                .setPrice("price_1KXU3NJfHH6VZXI5hOpgXs1a")//$0.50
                .build())
            .build();
      Session session = Session.create(params);

      response.redirect(session.getUrl(), 303);
      return "";
    });
  }
}