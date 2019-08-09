# Foodr

## Iteration 03 - Review & Retrospect

 * When: Dec. 1, 2017
 * Where: Physical and Online

## Process - Reflection

 * During iteration 2, we realized that we our process decisions were ineffective such as the lack of usage of the task board or an added overhead such as the previous Git workflow of using development branches and code reviews before merging. Thus, we decided to simply use Facebook Messenger as our primary process tool.

#### Decisions that turned out well
 
  * The decision to use Messenger for our task delegation has been successful. We were able to complete all our tasks by our established deadlines, which was the success metric we defined in our planning document. These tasks included adding the future features mentioned in our last iteration's demo video and the tasks related to the demo video for this iteration. This was extremely helpful for us since tasks quite often ended up depending on each other. For instance, for the goal of adding a distance option to our app, we split it up into two tasks where one team member was responsible for adding the back-end component and another team member was responsible for adding the front-end component and linking it to the back end. If the first team member ended up missing their deadline then the second team member would have been delayed as well. We never had this issue due to everyone being able to meet their deadlines.
  * The decision to use only the master branch for development has proven to be a good decision. One boon is that we do not have to deal with the unnecessary, additional overhead involved with branching. Additionally, we did not have any instances where a team member had to fix an issue they found in an earlier commit made by another team member. All team members were able to work on the master branch effectively and push changes without any noticeable issues. This means that the delay between implementing a change and when it was on the master branch without any issues was minimal. This satisfies our success metric for this process decision.
  * As stated in iteration 1 and iteration 2, the process decision to use Facebook Messenger as our main form of communication still proves to be a good choice. Additionally, we now use it to facilitate our task delegation, code review, and code changes. These additional activities have also been successful as stated in the above two points.
  
#### Decisions that did not turn out as well as we hoped
 
 * The decision to not have a physical meeting in the optional tutorial on November 20 resulted in a delayed start for this iteration. On that day, our team held a short online meeting where we went over what we need to implement to satisfy what we stated in the demo video. However, we never took the time to split up the goals into various tasks and delegate those tasks with their respective deadlines.
 
#### Planned changes

 * There are no planned changes for the demo preparation. We are happy with all the changes we have made so far for this iteration and have not noticed anything that is not working out as well as we hoped.

## Product - Review

#### Goals and/or tasks that were met/completed:

 * We completed implementing all of the features that we stated we were going to develop during iteration 2's demo video. As a part of this we also completed the following sub-goals:
     * We have added the distance filter as part of the [Settings](../Roulette/app/src/main/res/layout/settings.xml). Additionally, we also updated [SearchManager](../Roulette/app/src/main/java/com/example/jason/roulette/SearchManager.java) and [Yelp Fusion API](../Roulette/app/src/main/java/com/example/jason/roulette/YelpAPI.java) to utilize this new search parameter.
     * We have added the ability to users to go back from a result and continue swiping. This feature can be seen in our demo video for this iteration.
     * We have updated our [recommendation screen](../Roulette/app/src/main/java/com/example/jason/roulette/ResultActivity.java) to display a slideshow gallery of photos for that recommendation.
	 
 * We have also completed our second goal which was filming our prototype demo video as required for this iteration. This video can be found here: [Prototype Demo video](https://www.youtube.com/watch?v=9T7gCApR6mA&feature=youtu.be)
	* The video's script can be found [here.](./demoscript2.md)

* We also decided that another goal we wanted was to have our design documents more in sync with our video. Last iteration we highlighted user stories and use cases that were not documented in any design documents. This time, we had the design documents first, so it was much easier to come up with the script for the video since we already had these in place. We updated the following documents:
	* [Use Cases](./UseCase1.md)
 
    * [User Stories](./userStories.md)
    * Additionally, we updated our [CRC cards](./CRCCards.pdf) with our added features

#### Goals and/or tasks that were planned but not met/completed:

 * We were able to fully complete all our planned goals and tasks for this iteration.

## Meeting Highlights

Going into the next iteration, our main insights are:

 * Test our product and prepare our demo presentation to ensure that we do not encounter any application breaking bugs.
 * Add documentation to our code for completeness and clarity.
