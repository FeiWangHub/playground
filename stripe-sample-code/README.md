# Accept a Payment with Stripe Checkout

Stripe Checkout is the fastest way to get started with payments. Included are some basic build and run scripts you can use to start up the application.

## Running the sample

1. Build the server

~~~
mvn package
~~~

2. Run the server

~~~
java -cp target/stripe-payment-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.stripe.sample.Server
# or run in backend linux
java -cp target/stripe-payment-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.stripe.sample.Server log.file 2>&1 &
~~~

3. Go to [http://localhost:4242/checkout.html](http://localhost:4242/checkout.html)