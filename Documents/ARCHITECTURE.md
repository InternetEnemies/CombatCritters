# Architecture


![intial_architecture](https://cdn.discordapp.com/attachments/1194350823585624199/1286334117193191445/Untitled_Diagram.drawio11.png?ex=66eed900&is=66ed8780&hm=652fb9c204b9c72758bbbcbf5f97f6625ceacf03526de5ea8f2fbd4dded5d644&)

MAUI and React both provide good UI frameworks and can interface with a web api provided by spring. Since we are continuing a previous project and reusing some of the codebase, Java is required and spring boot seems like the commonly used library that will suit our requirements. Again since the codebase already exists, continuing with SQL makes sense and Postgres is free and open source. Using EC2(and S3/AWS overall) for hosting works well to meet our needs and it allows us to leverage other AWS services should the need arise.

