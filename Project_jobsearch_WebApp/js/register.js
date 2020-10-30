var config = {
    apiKey: "AIzaSyCeC7MjgPsyqEiMwNb3kLj_d9YKTkcW03g",
    authDomain: "job-search-i4gic.firebaseapp.com",
    databaseURL: "https://job-search-i4gic.firebaseio.com",
    projectId: "job-search-i4gic",
    storageBucket: "job-search-i4gic.appspot.com",
    messagingSenderId: "239085417537"
};
firebase.initializeApp(config);
var root= firebase.database().ref('Candidate_Registration_Form');
var j;
root.on("value",snap=>{
      j=snap.numChildren();
});
document.getElementById('registerform').addEventListener('submit', submitForm);

function submitForm(e){
    e.preventDefault();
  var firstname= getInputVal('firstname');
  var lastname= getInputVal('lastname');
  var birth= getInputVal('birth');
  var currentlocation= getInputVal('currentlocation');
  var contactnumber= getInputVal('contactnumber');
  var emailaddress= getInputVal('emailaddress');
  var jobtitle= getInputVal('jobtitle');

  saveMessage(firstname,lastname,birth,currentlocation,contactnumber,emailaddress,jobtitle,j);
}

function getInputVal(id){
    return document.getElementById(id).value;
}

function saveMessage(firstname,lastname,birth,currentlocation,contactnumber,emailaddress,jobtitle,j){
    var a=firstname.length;
    var b=lastname.length;
    var c=birth.length;
    var d=currentlocation.length;
    var e=contactnumber.length;
    var f=emailaddress.length;
    var g=jobtitle.length;

        if((a==0)||(b==0)||(c==0)||(d==0)||(e==0)||(f==0)||(g==0)){
            document.getElementById('demo').innerHTML="Information form is not enough!!";
        }
        else{
                j++;
                var u="User"+j;
                var info=root.child(u);
                info.set({
                Firstname: firstname,
                Lastname: lastname,
                Date_Of_Birth: birth,
                Current_Location: currentlocation,
                Contact_Number: contactnumber,
                Email: emailaddress,
                Job_Title: jobtitle,
            }); 
            document.getElementById('success').innerHTML="Your form has been submitted successfully";
            setTimeout(function(){window.location.href='jobs.html'},1000);
            return true;       
            }
        }  


