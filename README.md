Follow https://clojurescript.org/guides/webpack

You need:
- Deployed Ion
- Cognito Identity Pool with unauthenticated identities allowed. (authenticated role is also possible, just requires more cljs code)
- The policy for your ion code must allow the unauthenticated identities to execute the deployed lambda function (and NO OTHER LAMBDA FUNCTIONS!). I'm pretty sure this could be done automagically from the ion-config.edn file ;)

Modify `hello-webpack/src/cljs/hello_webpack/core.cljs` to use the identity pool you created and the name of the Ion lambda function.

When you open the page the lambda will be invoked.

Enjoy!
