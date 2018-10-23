(ns hello-webpack.core
  (:require [react]
            [lambda]
            [aws]))

(def identity-pool "us-east-1:my-identity-pool")

(aset aws "config" "region" "us-east-1")
(aset aws "config" "credentials"
      (js/AWS.CognitoIdentityCredentials.
       (js-obj "IdentityPoolId" identity-pool)))

(def lambda (js/AWS.Lambda. (js-obj "region" "us-east-1"
                                    "apiVersion" "2015-03-31")))

(def lambda-params (js-obj "FunctionName" "Your-lambda-Name"
                           "InvocationType" "RequestResponse"
                           "LogType" "None"
                           ;; payload is the input to your lambda
                           "Payload" "42"))

;; Invoke a lambda on page load
(.invoke lambda lambda-params (fn [err, data]
                                (if err
                                  (.log js/error err)
                                  (.log js/console (.parse js/JSON (aget data "Payload"))))))


(.log js/console (aget aws "config" "region"))
(.log js/console (aget aws "config" "credentials"))
(.log js/console react/Component)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Example javascript lambda code
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 	// Configure AWS SDK for JavaScript & set region and credentials
;; // Initialize the Amazon Cognito credentials provider
;; AWS.config.region = 'us-west-2'; // Region
;; AWS.config.credentials = new AWS.CognitoIdentityCredentials({
;;     IdentityPoolId: 'IDENTITY_POOL_ID',
;; });

;; 	/// VARIABLES AND DATA MAP NEEDED ///
;; 	var pullReturned = null;
;; 	var slotResults;

;; 	/// Prepare to call Lambda function
;; 	lambda = new AWS.Lambda({region: 'us-west-2', apiVersion: '2015-03-31'});
;; 	var pullParams = {
;; 		FunctionName : 'slotpull',
;; 		InvocationType : 'RequestResponse',
;; 		LogType : 'None'
;; 	};

;; /// CLIENT UI CODE ///

;; 	// Application global variables
;; 	var isSpinning = false;

;; 	function pullHandle() {
;; 		if (isSpinning == false) {
;; 			// Show the handle pulled down
;; 			slot_handle.src = "lever-dn.png";
;; 		}
;; 	};

;; 	function initiatePull() {
;; 		// Show the handle flipping back up
;; 		slot_handle.src = "lever-up.png";
;; 		// Set all three wheels "spinning"
;; 		slot_L.src = "slotpullanimation.gif";
;; 		slot_M.src = "slotpullanimation.gif";
;; 		slot_R.src = "slotpullanimation.gif";

;; 		// Set app status to spinning
;; 		isSpinning = true;
;; 		// Call the Lambda function to collect the spin results
;; 		lambda.invoke(pullParams, function(err, data) {
;; 			if (err) {
;; 				prompt(err);
;; 			} else {
;; 				pullResults = JSON.parse(data.Payload);
;; 				displayPull();
;; 			}
;; 		});
;; 	};

;; 	function displayPull() {
;; 		isSpinning = false;
;; 		if (pullResults.isWinner) {
;; 			winner_light.visibility = visible;
;; 		}
;; //		prompt(JSON.stringify(pullResults));
;; 		slot_L.src = pullResults.leftWheelImage.file.S;
;; 		slot_M.src = pullResults.middleWheelImage.file.S;
;; 		slot_R.src =  pullResults.rightWheelImage.file.S;
;; 	};
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
