# Intralot Code Exercise
The purpose of this repository is for the Code Exercise that I was tasked to complete by Intralot.


## What would you change to allow other types of medical professionals, not only PTs?
  > What I would change to enable different types of medical professionals and not just Physical Therapists to be displayed would be to modify the endpoint/URL to show more than only PTs. If possible, I would reuse the existing data model and code as it would allow me to make the switch rather quickly.

## If we need to add our own review system:
  **a) What would you change in your solution to display both reviews (yelp and built-in review system) in the same View?** 
  > The change I would make in my solution to display both review systems in the same view would be to have that specific textView output both reviews. An example of how I would accomplish this would be to use a separator (e.g., 'Yelp Rating: 3.5 | Our Rating: 4.0'.) By doing it this way, I can keep most of the same code and have to display the additional data alongside the yelp review data.
  
  **b) Do you think the backend should have preprocessed all reviews and send it to the app, or yelp API calls should still reside on the app?**
  > I think the backend should preprocess all the reviews and send it to the app as this would help with having to do unnecessary API calls when this could all be consolidated by having the backend handle it and then calling the data from the backend and displaying it on the client (mobile app).
  
  **c) What are the advantages and drawbacks of choosing one over the other one?**
  > The advantage of choosing one over the other is that when you have the backend preprocess all the reviews, you don't have to make that call on the client-side, thus it allows you to save both lines of code written and execution time of the application. A drawback of this direction is that it is very data-centric. Having the backend preprocess the data means that the data comes first and the code second. Which means you cannot have long-running functions.
