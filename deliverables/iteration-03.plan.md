# Foodr

## Iteration 03

 * Start date: Nov. 17, 2017
 * End date: Dec. 1, 2017

## Process

 * As stated in our previous review document, we have unanimously decided to no longer use our task board and branches as a part of our workflow and solely use our group chat on Facebook Messenger to do all our task planning and delegation, and code review and discussion. The main reason for making this change is because the task board and branches were not working out for us and ended up hindering our progress.

#### Changes from previous iteration

 * The most significant change is the change in how we keep track of tasks and delegate them. Previously, we were using [GitHub Projects task boards](https://github.com/csc301-fall-2017/project-team-02/projects) for tasks and allowing team members to take up unassigned tasks of their own initiative. For this iteration, we are now assigning tasks as part of a group discussion and then updating each other as we progress through our respective tasks. We are making this change because the task board ended up being rarely used, let alone looked at due to the fact that we usually decided on tasks as a group, so the task board became an extra, unnecessary step. Additionally, tasks usually ended up being unclaimed due to team members assuming that others will end up doing them, and therefore tasks ended up being uncompleted. We believe that delegating tasks directly is going to allows us to complete tasks in a more timely manner as it then becomes very clear as to what we are each responsible for and when they need to be done by. Our success metric is the percentage of tasks we are able to finish by the time we say we will have them done by. Last iteration had a lot of instances where tasks ended not being completed by the time we said they should be done by. For this iteration, we are hoping to reduce that percentage.
 * The next change is about our decision to not use Git branches for code organization and review and only use the master branch. Last iteration, the plan was to have each team member have their own branch and then have another team member do a code review before any merges. Now we are just using the single master branch and updating each other as we commit as to what changes we are making and why. This change is due to the difficulty we had in managing and merging the various branches, and due to not seeing any benefit from using this workflow. Our group tends to work on different sections of the codebase, so conflicts have rarely been an issue for us, and we work at different times of the day, so it was hard to get another person to do a timely review. Our success metric will be how long it takes between when one of us complete something that we want to commit and when that change is on the master branch without any issues. With branches, it took a while for this to happen with the major delay being having another person to review the changes. With this change to our workflow, the commits will be immediately available on the master branch which forces everyone to have to review the changes since we have to pull and make sure the changes do not affect our code before we can push our own changes. This ensures that everyone is looking over everyone else's code, so we hope to be able to quickly spot any issues and deal with them in a quicker and more timely manner.

#### Roles & responsibilities

These roles and responsibilities are the same as from Iteration 1 and 2. We have yet to encounter any problems with these divisions of duties, so we saw no reason to change them.

Product Owner: Jason

 * This individual is responsible for the overall direction of the product. This includes understanding the overall needs of users, how the product will be able to meet those needs, and the additional benefits the product will be able to provide.

Development Team: Everyone

 * These people are responsible for transforming high-level needs, features, and designs into concrete tasks and then implementing them into code. This includes writing code, documenting code, and then testing code.

Designers: Denzel, Michael

 * These people are responsible for designing the visual representation of the product. This includes producing layouts and mock-ups of what users will see when using the product.

Writers: Andrew, Donna

 * These people are responsible for the write up and proofreading of the meeting documents.

#### Events

November 20 (Online group meeting): Discuss remaining features

* Review video and confirm which features need to be implemented
* Divide the work among the team members and when we want to have them done by

November 27 (Tutorial):

* Review the video with the TA
* Plan changes for the new video
* Confirm the division of tasks and their respective deadlines

November 29 (Online group meeting):

* Update members on each other's progress
* Have TA look over script for new video

December 1 (In person meeting):

* Film and record the necessary parts for the video
* Finalize features, video and submit

 
#### Artifacts

 * Our [GitHub project boards](https://github.com/csc301-fall-2017/project-team-02/projects) was our main method of tracking the tasks that need to be done for this iteration. We had a general board for more high-level documents and then a project board for the front end and back end each. They all consist of three columns: To Do, In Progress, and Done. Tasks near the top of the To Do column are what we consider to be most important.
     * Each week in our meetings we review the tasks from the previous week, then we each decide which tasks we want to take up for the next week and whether new tasks need to be added.
     * Using the project board has proven to be unreliable and it is no longer in use

 * Main communication channel
     * We have been using Facebook messenger as our main form of communication. We use it to discuss and delegate tasks, as well as update each other on the details of our progress.

#### Git / GitHub workflow

Our Git workflow has been changed from the last iteration to simply push to the master branch
 * We have also made the decision to make a comment in Messenger whenever we have finished working on the code for that work period. In which case, another team member can choose to look over the code.
 * We have adopted the Java standard naming convention. One such example is that we use camel case for variables and methods. The reason for this is that it is the convention we are most familiar with.
 * To iterate again, we chose this workflow because we discovered that our team members work on the application code at different times of the day. As an example, some of our team members work on the code in the afternoon while others work on it past midnight. Thus, it increases our efficiency in comparison to the old workflow of using development branches.
 
## Product

#### Goals and tasks

 * Develop a working Android application to demo our main use case:
     * Add option for users to specify a distance within which they want results for
     * Add the ability for users to go back from a result and continue swiping
     * Add a feature to the result screen that allows users to look at multiple pictures for the recommended restaurant
 * Film demo video
     * Write a revised video script
	 * Film test video
	 * Film final demo video
 * Update design documents to be in sync with what we will highlight in our video
     * Use case
     * User story

#### Artifacts
 * Code
	* The most essential part of the project. We are planning on making a working android application for the MVP.
 * CRC Cards
	* This is essential for deciding how our classes are related and allows for splitting work into modules. Useful to quickly see what each class is responsible for.
 * Use Cases
	* Update our current one, and add new ones if possible.
 * User Stories
    * A useful tool to design our target audience. Will be useful to further define our problem statement.
 * Personas
	* Update our current ones and write new ones if needed.
