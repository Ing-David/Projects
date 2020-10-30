var job1 = firebase.database().ref().child("Jobs").child("job1");
var job2 = firebase.database().ref().child("Jobs").child("job2");
var job3 = firebase.database().ref().child("Jobs").child("job3");
var job4 = firebase.database().ref().child("Jobs").child("job4");
var job5 = firebase.database().ref().child("Jobs").child("job5");
var job6 = firebase.database().ref().child("Jobs").child("job6");
var job7 = firebase.database().ref().child("Jobs").child("job7");
var job8 = firebase.database().ref().child("Jobs").child("job8");
var job9 = firebase.database().ref().child("Jobs").child("job9");
var job10 = firebase.database().ref().child("Jobs").child("job10");
var job11 = firebase.database().ref().child("Jobs").child("job11");
job1.on("child_added", snap => {
    
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#teacher1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply Now >> </button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Provide teaching  in accordance  the  standards expected  ACE</li>
                    <li>Develop lesson plans  supplementary teaching materials  necessary  help students learn  effectively  addition  using ACE curriculum  materials</li>
                    <li>Supervise  keep records  relevant student assessments  exams</li>
                    <li>Keep accurate records  student class attendance,  study progress  provide ongoing support.</li>
                    <li>Strictly adhere  the academic policies  approach endorsed  ACE</li>
                    <li>Participate  or conduct relevant  development sessions</li>
                    <li>Assist  relevant academic  public relation  and events</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>Cambodian and Native Speaker Of English</li>
                    <li>Recognised university degree  an English Language Teaching field</li>
                    <li>Good command  English language proficiency (minimum IELTS overall band score  6.5) –  might  required  take  IELTS test  demonstrate  required IELTS score</li>
                    <li>Demonstrated good understanding  teaching methodology  pedagogical knowledge</li>
                    <li>Demonstrated enthusiasm  further learning,  development  academic excellence</li>
                    <li>Strong commitment  professional  personal integrity  ethical conduct</li>
                    <li>Ability  work  a multicultural environment </li>
                    <li>Commitment  participate  relevant CSR  and initiatives  ACE</li>
                </ul>
            </div>
        </div>
    `);
});

job2.on("child_added", snap => {
    
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#it1').append(`
        <div class="job-content-title">
            <div class="left-content">
                <div class="job-title">${position}</div>
                <div class="job-location">${company}, ${location}</div>
            </div>
            <div class="right-content">
                <button type="Search" class="btn btn-primary">Apply at employer's site</button>
            </div>    
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Design build advanced app features the iOS platform.</li>
                    <li>Independently craft solutions by applying solid Object-Oriented-Design principles.</li>
                    <li>Working a team talented iOS developing amazing native apps.</li>
                    <li>Work closely product management & UX execute idea concept delivery using excellent software design, coding, & processes.</li>
                    <li>Continuously discover, evaluate, implement technologies maximize development efficiency</li>
                    <li>Create maintain software documentation.</li>
                    <li>Providing technical recommendations improve product.</li>
                    <li>Using best practices  coding ensuring product at highest quality.</li>
                    <li>Identify correct bottlenecks fix bugs.</li>
                    <li>Design flows new products services.</li>
                    <li>Maintain exiting app develop IOS Applications.</li>
                    <li>Manage publish version update App store.</li>
                    <li>Other tasks assign by Management.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>BS/MS degree Computer Science, Engineering a subject.</li>
                    <li>2+ of software development experience.</li>
                    <li>2+ of experience iOS, Objective-C, Swift.</li>
                    <li>Hands experience to HTML5, CSS, XML, JSON, Node JS, API.</li>
                    <li>Coursework Object-Oriented languages (C++/Java, etc.).</li>
                    <li>Must have experience  Mac OS platforms.</li>
                    <li>A passion technology the ability learn  concepts quickly.</li>
                    <li>Solid understanding the full mobile development life cycle.</li>
                    <li>Experience Payment applications integrations would preferable. </li>
                    <li>Understanding code versioning GitLab GitHub tool.</li>
                </ul>
            </div>
        </div> 
    `);
});

job3.on("child_added", snap => {
    
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#accountant1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Develop products.</li>
                    <li>Create idea marketing’s campaign (ATL & BTL).</li>
                    <li>Branding proposal execution plan.</li>
                    <li>Promotion planning, organization execution.</li>
                    <li>Attending Team Meeting Sharing best practice colleagues.</li>
                    <li>Work the in-house designer produce materials visual impact within brand guidelines POSM.</li>
                    <li>Support execute any events.</li>
                    <li>Any tasks assigned by Management.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>Male/Female.</li>
                    <li>Hold bachelor degree marketing related field/fresh graduated.</li>
                    <li>One experience marketing.</li>
                    <li>Willing go out side.</li>
                    <li>Self-confident, honest hard working.</li>
                    <li>Friendly Flexible.</li>
                    <li>Can Speak writing English.</li>
                    <li>Good Communication customer.</li>
                    <li>Have own transportation ( Motor). </li>
                    <li>Willing learn thing.</li>
                    <li>Be able work under pressure.</li>
                </ul>
            </div>
        </div>
    `);
});

job4.on("child_added", snap => {
    
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#service1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>Restaurant Service be request work area :
                    <li>Cashier, Back up, Cooking, Customer Service, Delivery.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>Male Female, Age 18 above.</li>
                    <li>Can Read Write language some English.</li>
                    <li>Own Vehicles.</li>
                    <li>Be honest, initiative, creativity, commitment good interpersonal.</li>
                </ul>
            </div>
        </div>
    `);
});

job5.on("child_added", snap => {
    
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#manager2').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Grow  business through  volume,  client acquisition  increasing  number  products held per client.</li>
                    <li>Develop implement client strategies conjunction product partners relevant parties identify right and opportunities.</li>
                    <li>Proactively developing maintaining detailed understanding your clients business financial (account planning).</li>
                    <li>Develop effective relationships overseas colleagues deliver seamless to clients.</li>
                    <li>Ensure quality portfolio through sound credit assessment, lending decisions accurate account management.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>Strong knowledge financial statement credit assessment incorporation (background business lending).</li>
                    <li>Experience a corporate business banking environment.</li>
                    <li>Demonstrated understanding local corporates business and expectations.</li>
                    <li>A problem solver  has  strong client focus.</li>
                    <li>Ability work autonomously can with team.</li>
                    <li>Tertiary qualification a business field.</li>
                    <li>Strong communication both written verbal well verse computer applications tools.</li>
                    <li>Competencies: Focuses the Customer, Broadens Perspective, Delivers Results, Exercises Sound Judgment.</li>
                </ul>
            </div>
        </div>
    `);
});

job6.on("child_added", snap => {  
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#manager1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Build good relationship related clients looking business opportunity.</li>
                    <li>Coordinate sales business initiatives integrate sales process across company the market.</li>
                    <li>Determine clients’ current future advertising marketing needs, creating customized solutions closing sales retained incremental revenue.</li>
                    <li>Attend meeting clients understand  needs get project brief.</li>
                    <li>Generate sophisticate customized sales proposals present clients.</li>
                    <li>Research understand brand communication certain brands.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>Possesses ability create, maintain enhance customer relationship.</li>
                    <li>Knowledge the local international digital media with ability respond effectively market direction, client and competition.</li>
                    <li>Deep relationships local advertising agencies clients.</li>
                    <li>Strong communication including verbal, written formal presentation which clear, concise tailored various audiences.</li>
                    <li>Ability establish rapport, develop credibility sell ideas senior management.</li>
                    <li>Must have ability maintain professional approach others job-demanding multitasking and/or support situations.</li>
                </ul>
            </div>
        </div>
    `);
});

job7.on("child_added", snap => {  
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#hr1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Assist delivery volume recruitment processes guaranteeing sustainable results constant ROI improvement the recruitment team.</li>
                    <li>Develop understanding role requirements develop collaborative relationships the recruitment team, hiring managers appropriate.</li>
                    <li>Manage key relationships recruitment manager, hiring and other stakeholders ensure efficient timely execution the end end recruitment process starting posting jobs the on-boarding process candidates.</li>
                    <li>Ensure knowledge compliance all relevant HR and Recruitment processes.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>Proven experience working recruitment candidate onboarding.</li>
                    <li>Experience proactive sourcing strong experience using HR systems.</li>
                    <li>Strong coordination, relationship management customer skills.</li>
                    <li>Has keen attention details process orientation.</li>
                </ul>
            </div>
        </div>
    `);
});

job8.on("child_added", snap => {  
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#supervisor1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Maintenance computer electronic device Cinema.</li>
                    <li>Maintenance movie projector.</li>
                    <li>Maintenance Cinema System.</li>
                    <li>Check schedule movie vista prepare movie show everyday.</li>
                    <li>Check period Lamp Projector.</li>
                    <li>inject movie server.</li>
                    <li>Check spot expire day.</li>
                    <li>Check KDM key movie.</li>
                    <li>Check subtitle movie.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>At least 1 of experience to field.</li>
                    <li>Bachelor degree IT Engineering Computer Science.</li>
                    <li>Good in problem solving troubleshoot.</li>
                    <li>Dynamic, motivated, well organized.</li>
                    <li>Flexible be able meet deadlines.</li>
                    <li>Be able work under pressure.</li>
                </ul>
            </div>
        </div>
    `);
});

job9.on("child_added", snap => {  
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    var duration = snap.child('duration').val();
    $('#eng1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duration : ${duration}</span><br>
                <span>Duties:</span>
                <ul>
                    <li>This internship response on supporting Business business for developing pipeline and closing projects selling power contracts into the Commercial & Industrial customers in Cambodia.</li>
                    <li>Help to research develop list potential target of the market including identification of group individual customer and market opportunities by each segment.</li>
                    <li>Identify list big investors large energy consumers the nation.</li>
                    <li>Select develop list medium small customers.</li>
                    <li>Search, analyses prepare details information target customers the customer list such the energy consumption its power demand capacity.</li>
                    <li>Identify potential network channel events.</li>
                    <li>Follow up the task assigned instruction given by Sales Director Business Developer.</li>
                    <li>Good and a good team work.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>Educational background: Bachelor of Electrical Energy Engineering skills to solar energy.</li>
                    <li>Skills: Good communication skills and good technical knowledge renewable energy (Solar).</li>
                    <li>Knowledge: English is the working language but knowledge of other languages could help.</li>
                    <li>Broad network across Cambodia.</li>
                    <li>Working in dynamic and fast-pace environment.</li>
                </ul>
            </div>
        </div>
    `);
});

job10.on("child_added", snap => {  
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#shipping1').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Handle daily documentation export Sea/Air shipment (HBL/ HAWB).</li>
                    <li>Coordinate shipper booking approval made.</li>
                    <li>Coordinate shipping line/air line booking process.</li>
                    <li>Coordinate sale & marketing  ensure shipment proper arrange customer require rate approved.</li>
                    <li>Follow comply SOP.</li>
                    <li>Other task per assigned.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>University degree.</li>
                    <li>At least 2 experience Shipping/Freight forwarding industry.</li>
                    <li>Excellence command Chinese English a must, Japanese a plus.</li>
                    <li>Computer (Microsoft word, excel, outlook, power point).</li>
                    <li>Be flexible problem solving skill.</li>
                    <li>Ability work under tight deadline pressure.</li> 
                </ul>
            </div>
        </div>
    `);
});

job11.on("child_added", snap => {  
    var position = snap.child('position').val();
    var location = snap.child('location').val();
    var schedule = snap.child('schedule').val();
    var company = snap.child('company').val();
    var salary = snap.child('salary').val();
    $('#shipping2').append(`
        <div class="job-content-title">
                <div class="left-content">
                    <div class="job-title">${position}</div>
                    <div class="job-location">${company}, ${location}</div>                      
                </div>
                <div class="right-content">
                    <a href="sign-in.html"><button type="Search" class="btn btn-primary">Apply at employer's site</button></a>
                </div>
        </div>
        <br>
        <div class="job-content-body">
            <div>
                <span>Description:</span>
                <ul style="list-style-type:none;">
                    <li>Location: <span>${location}</span></li>
                    <li>Schedule: <span>${schedule}</span></li>
                    <li>Salary: <span>${salary}</span></li>
                </ul>
                <span>Duties:</span>
                <ul>
                    <li>Handle daily documentation import Ocean/Air shipment (POD/Authorize…).</li>
                    <li>Coordinate overseas counterpart /consignee /shipping line /air line arrival shipment.</li>
                    <li>Coordinate customs permit & customs clearance department.</li>
                    <li>Coordinate sale & marketing ensure shipments proper arrange customer require rate approved.</li>
                    <li>Follow up comply SOP.</li>
                    <li>Other task per assigned.</li>
                    <li>Experience clearance.</li>
                </ul>
                <span>Requirement:</span>
                <ul>
                    <li>University degree.</li>
                    <li>At least 1-2 experience  Shipping /Freight forwarding industry.</li>
                    <li>Excellence command English a must, Chinese /Japanese a plus. -Computer (Microsoft  word, excel, outlook, power point)-Be flexible problem solving skill.</li>
                    <li>Ability  work under tight deadline  pressure.</li>
                </ul>
            </div>
        </div>
    `);
});

