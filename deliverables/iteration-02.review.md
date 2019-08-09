# Foodr

## Iteration 02 - Review & Retrospect

 * When: Nov. 16, 2017
 * Where: Physical and Online

## Process - Reflection

There are three major things we would like to talk about in our process reflection.
1. Communication: Continuing from iteration 1, we have continued to use Facebook Messenger as our primary method of communication.
2. Task delegation: As of this iteration, we have decided to use GitHub project boards to track tasks and our progress, with team members taking up tasks of their own volition.
3. Design process: Continuing from iteration 1, we have strived to increase the quantity and quality of design documents created.

#### Decisions that turned out well
 
  * During October 26's online meeting we made the decision to pivot our initial design of randomly displaying restaurants for users to browse through to a more user focused approach where we display food images and have users indicate which ones they like or dislike and then use those preferences to display a more informed, tailored results. We strongly believe that this change has been for the better as it allowed us to refocus our design to address a better-defined problem and target audience. We shifted our target audience from indecisive people to people who want to try something new and have some initial idea of what they like or dislike. It has been easier for us to imagine what users would expect from our application and therefore easier for us to make decisions regarding our features. This decision was made after input from our TA who addressed some of the issues with our initial idea such as the issue where if a user is indecisive, they would still be indecisive no matter what recommendation we present to them.
 
  * As stated in iteration 1's review, the process decision to use Facebook Messenger as our main form of communication still proves to be a good choice. We have found it much more efficient and easier to use this as our method of brainstorming tasks, delegating tasks, and updating each other on our progress. We have also found it to be more convenient to use for discussing code changes and conduct code reviews. As stated in the following section, we have supplanted the GitHub project boards and Git workflow of branching to just using Messenger. 
  
#### Decisions that did not turn out as well as we hoped
 
 * The idea to use [GitHub project boards](https://github.com/csc301-fall-2017/project-team-02/projects) for task tracking was rarely used as we found it more efficient to use Facebook Messenger to do all of our task brainstorming, delegation and tracking. As this is a fairly small project and we are a relatively small team, we found the project broads and additional branches to be too much overhead that was impeding our progress instead of helping it. Tasks were always small and could be completed quickly so usually whoever came up with a task would just do it rather than go through the process of adding it to the board and moving it across the columns. If there were multiple tasks that needed to be done, it was always faster to just ask someone if they could do the task instead of adding it to the board and waiting for someone to take it up. There were also never enough tasks being done at once that we lost track of who was doing what at any given time. 
 
 * Using the planned Git workflow for our codebase of pushing to branches before merging did not work as well as we hoped. We realized that we individually work at different times of the day meaning that it was hard for changes to be reviewed in a timely manner. In addition, we realized that due to our small team we were rarely working on the same section of code at the same time, so we rarely ran into conflicts by just using the master branch. We also found that our discussions in our group chat were more than enough to do general code reviews and keep everyone informed as to the progression of our code. The idea of using multiple branches and pull requests was essentially over-engineering our workflow and ended up being not very useful.

 * The idea that team members would take the initiative to take up tasks on their own did not turn out as well as we expected. Tasks often ended up being ignored due to the belief that another team member would take it up instead. As a result, there was little progress during the first couple of weeks where we had a lot of task ideas, but no one was doing them. This is best exemplified by the lack of usage of the project board. Tasks initially got added, but then no one would take them up. As a result, we ended up having more established times for delegating tasks and clearly defining what everyone was doing and when they needed to be done by. This type of planning is better facilitated in a group chat environment rather than a project board.
 
#### Planned changes

 * Moving forward, we will be using Facebook Messenger as our central organizing tool to delegate tasks, review code, plan merges, communicate issues, and update each other's progress. We are formalizing this change as it seems to be our de facto method and has been working really well for us up until now. Our initial plan ended up overcomplicating our workflow without any noticeable benefit, so it would be a waste of our time and effort to try alternative methods for task boards and merge conflict resolution when they clearly have not been as well adopted.

## Product - Review

#### Goals and/or tasks that were met/completed:

 * We completed one of our two main goals and were able to develop a working Android application for our demo video. As a part of this we also completed the following sub-goals:
     * We have a [Settings screen](../Roulette/app/src/main/res/layout/settings.xml) for user options and we use our [CardsActivity](../Roulette/app/src/main/java/com/example/jason/roulette/CardsActivity.java) to handle user input regarding their like/dislike preferences.
     * We have integrated the [Yelp Fusion API](../Roulette/app/src/main/java/com/example/jason/roulette/YelpAPI.java) which utilizes Yelp's powerful database as our main search engine. Additionally, we have integrated the [Google Places API](../Roulette/app/src/main/java/com/example/jason/roulette/PlacesAPI.java) which is used to gather food photos for our results.
     * We implemented a [SearchManager class](../Roulette/app/src/main/java/com/example/jason/roulette/SearchManager.java) to store user's input and pass them along to our APIs, as well as store the results we receive back.
    * Additionally, we have developed the general layout and user interface as seen in [our start screen](../Roulette/app/src/main/res/layout/start_screen.xml), [our images displayed as cards](../Roulette/app/src/main/res/layout/stock_card.xml), [and our application's recommendation.](../Roulette/app/src/main/res/layout/restaurant_result.xml)
	
 * We have also completed the second of our two goals which was filming our demo video as required for this iteration. This video can be found here: [Demo video](https://www.youtube.com/watch?v=6wc35sUzxMw)
	* The video's script can be found [here.](./demoscript.md)

* We also decided that another goal we wanted was to have more design documents. These are the ones we have been able to complete:
    * [CRC cards](./CRCCards.pdf)
 
    * [Layout mock-up](./InitialLayoutMockup.jpg)
 
    * User Persona: [Steve](https://app.xtensio.com/folio/hlo3l0ug)

#### Goals and/or tasks that were planned but not met/completed:

 * The task of design document creation was planned but not fully completed. As stated in our decisions that did not turn out well section, our progress on this iteration was initially delayed which has led to a greater focus in developing the application rather than the documents. We only created the bare minimum of artifacts to focus our design decisions. One thing that we have mentioned a lot in our group discussion was user stories. In our demo video we mentioned a few of these and they were in the back of our minds as we were developing the application, but we never formalized them in a document.

## Meeting Highlights

Going into the next iteration, our main insights are:

 * As stated in our planned changes, our process moving forward is to reduce the unnecessary overheads and streamline our workflow by solely using Facebook Messenger to coordinate our efforts. The project board and branches were not well adopted by us, so it would be better for us to stay with what works for us and not overcomplicate our workflow.
 * The change in product design has been a boon to our team as we now have a focused problem and target audience with a much clearer solution. Moving forward we can continue to improve upon our application with this focus in mind and ensure that any additional features we choose to add are working to better-develop our application as a solution to this problem.
