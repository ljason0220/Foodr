| Name | UC-1: User uses Foodr |
| --- | --- |
| Primary Actor | App user |
| Precondition | A user accesses Foodr mobile app |
| Success Guarantees | A recommended restaurant is returned. |
| Main Success Scenario | 1. User starts a new search <br> 2. User is presented with sixteen stock cuisine cards and swipes or clicks the buttons to indicate interest <br> 3. User is presented with image cards representing locations nearby that serve their cuisine choices <br> 4. User is presented with a recommendation based on their preferences <br> 5. User is satisfied with the recommendation and clicks button to open google maps for the directions
| Extensions | 1a. User not satisfied with filters <br>   1a1. User quits app <br>   1a2. User changes filters for price and distance <br> 4a. User is not satisfied with suggestion <br> 4a1.   User quits app <br> 4a2.   User clicks the back arrow to go through another set of cards from the same search settings <br>   4a3. User changes filters and starts a new search|
| Postcondition | A restaurant is returned, user closes app |
