# Yelp Roulette

## Iteration 02

 * Start date: Oct. 16, 2017
 * End date: Nov. 16, 2017

## Process

#### Roles & responsibilities

These roles and responsibilities are the same as from Iteration 01. We have yet to encounter any problems with these divisions of duties, so we saw no reason to change them.

Product Owner: Jason

 * This individual is responsible for the overall direction of the product. This includes understanding the overall needs of users, how the product will be able to meet those needs, and the additional benefits the product will be able to provide.

Development Team: Everyone

 * These people are responsible for transforming high-level needs, features, and designs into concrete tasks and then implementing them into code. This includes writing code, documenting code, and then testing code.

Designers: Denzel, Michael

 * These people are responsible for designing the visual representation of the product. This includes producing layouts and mock-ups of what users will see when using the product.

Writers: Andrew, Donna

 * These people are responsible for the write up and proofreading of the meeting documents.

#### Events

Oct. 16 (tutorial): Task list and Workflow

 * Create and populate task board
 * Document GitHub workflow for Deliverable 02
 * Ensure everyone has a development environment to build an Android application
 
Oct. 23 (tutorial): Explore tools and Implementation design

 * Basic starter code for a "Hello, World!" Android app
 * Create minimal working code for each API we want to use
 * CRC cards for the classes we plan to implement
 * Design layout for each distinct screen users will see
 
Oct. 26 (online group meeting): Delegate tasks to team members for the upcoming weekend

 * The idea of the app is being changed so we need to update our documents
 * Decide important documents and artifacts to have created by next tutorial's presentation
 
Oct. 29 (online group meeting): End of weekly iteration

 * Decide what we're presenting for the next day's tutorial
 * Check up on what is finished and discuss next week's tasks
 * Team does code review of this week's code
 * Decide which APIs we are implementing for the MVP
 
Oct. 30 (tutorial): Discuss project progress and direction

 * Ask TA for advice on the new project direction

Nov. 5 (online group meeting): End of weekly iteration

 * Team does a code review of this week's code
 * Decide if we've finished the MVP, if not we need to make sure it is done during this week as we still need to film it
 * Decide which features we want to show in the demo video
 * Write demo video script
 * Decide when we're filming the demo video. A factor for the date to film includes having enough time to test to ensure stable features.
 
Date TBD (offline group meeting): Film the video

 * Review the video as a group.
 
Nov. 12 (online group meeting): End of weekly iteration

 * Decide what we're presenting for the next day's tutorial
 * Check up on what is finished and discuss next week's tasks
 * Team does a code review of this week's code

Nov. 13 (tutorial): Iteration 02 Review meeting

 * Write iteration-02.review.md
 * Decide if demo video needs to be reshot
 
#### Artifacts

 * Our [GitHub project boards](https://github.com/csc301-fall-2017/project-team-02/projects) will be our main method of tracking the tasks that need to be done for this iteration. We have a general board for more high-level documents and then a project board for the front end and back end each. They all consist of three columns: To Do, In Progress, and Done. Tasks near the top of the To Do column are what we consider to be most important.
     * Each week in our meetings we review the tasks from the previous week, then we each decide which tasks we want to take up for the next week and whether new tasks need to be added.

 * Main communication channel
     * We have been using Facebook messenger as our main form of communication. We use it to discuss and delegate tasks, as well as update each other on the details of our progress.

#### Git / GitHub workflow

Our Git workflow has some slight differences depending on if we are working on high-level design documents or on the codebase:
 * 1. For design documents, team members push their changes directly to the master branch. The reason for this is that each design document will have one member assigned to work on it so there should not be any conflicts. Any issues with the document can be brought up with the team member directly.
 * 2. For the codebase, we plan to have a branch for each team members to push their changes to. This is in order to avoid conflicts in the master branch. 
 * When a team member wants to merge their branch with the master branch, they will ensure that the application compiles and that any previously implemented features are not compromised. We will also have at least one other team member do a review of it beforehand. This is intended to catch any errors before they can be introduced in the master branch.
 * Each weekend, we plan to have the entire team collectively review newly written code in depth to ensure that minor bugs were not introduced. From there we can decided if we want to do a quick fix or roll back to an earlier revision on a case by case basis.
 
 * We chose this workflow for the design documents because these documents are what we design our code around so changes to these should be available as soon as possible. As for the codebase, we want to ensure a reasonable level of confidence in the code we merge into the master branch, but we also want to have frequent merges so that everyone has the newest implemented features. The compromise we decided on was to only have one other team member look over the changes.

## Product

#### Goals and tasks

 * Develop a working Android application to demo our main use case:
     * Screen for a user to input options and preferences.
     * Process user's input and use them to query our APIs.
     * Integrate Yelp! and Google Places API into our application.
     * Back end data structures to store input and results.
     * Screen for users to view and decide on results.
     * Screen to display chosen result and begin navigation.
 * Film demo video
     * Write video script
	 * Film test video
	 * Film final demo video

#### Artifacts
 * Code
	* The most essential part of the project. We are planning on making a working android application for the MVP.
 * CRC Cards
	* This is essential for deciding how our classes are related and allows for splitting work into modules.
 * Layout mock-up
	* Decide what is displayed on the application's screens. This allows us to decide what buttons and menus are required.
 * Use Cases
	* Update our current one, and add new ones. We need a good grasp on the user's interaction with our application to design it.
 * User Stories
    * A useful tool to design our target audience. Will be useful in our tutorial presentation.
 * Personas
	* Update our current ones and write new ones if needed.
