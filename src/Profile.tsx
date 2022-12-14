import React,{ useState, useEffect } from 'react';
import ReactDOM from "react-dom";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import { TableRowsTwoTone } from '@mui/icons-material';


import Login from './Login';
import emailID from "./Login"
import { mainuseremail } from './Login';
import { GoogleLogin, GoogleLogout} from 'react-google-login';


import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getDatabase, ref, set, onValue } from "firebase/database"
import { createNull, isFunctionDeclaration, isNonNullExpression } from 'typescript';

//MY MATCHES PAGE




function Profile (){


  function createData(
    // matchType: string,
    name: string,
    pronouns: string,
    email: string
  ) {
    return {name, pronouns, email };
  }

   const rowsStudy = [
    createData('', '', ''),
   ];

   const rowsFriend = [
    createData('', '', ''),
   ];

   const rowsDate = [
    createData('', '', ''),
   ];

  

  //  function DisplayButtonD() {

  //   return (
  //     <div className = "button-div">
  //       <Button id ="button-style2" variant="outlined" onClick={handleSubmitDate}>
  //           <label htmlFor="usernameInput">Click for DATE matches</label>
  //       </Button>
  //     </div>
  //     )
  //  }

  //  function DisplayButtonS() {

  //   return (
  //     <div className = "button-div">
  //       <Button id ="button-style2" variant="outlined" onClick={handleSubmitStudy}>
  //           <label htmlFor="usernameInput">Click for STUDY matches</label>
  //       </Button>
  //     </div>
  //     )
  //  }

  //  function DisplayButtonF() {

  //   return (
  //     <div className = "button-div">
  //       <Button id ="button-style2" variant="outlined"onClick={handleSubmitFriend}>
  //           <label htmlFor="usernameInput">Click for FRIEND matches</label>
  //       </Button>
  //     </div>
  //     )
  //  }
 

  //  function DisplayButtons(){
  //   if (avail.length === 3) {
  //     return (
  //       <div className = "button-div">
  //         <DisplayButtonD />
  //         <DisplayButtonS />
  //         <DisplayButtonF />
  //       </div>
  //     )
  //   } else if (avail.length === 1 && avail[0] === "users-date") {
  //     return (
  //       <div className = "button-div">
  //         <DisplayButtonD />
  //       </div>
  //     ) 
  //   } else if (avail.length === 1 && avail[0] === "users-study") {
  //     return (
  //       <div className = "button-div">
  //         <DisplayButtonS />
  //       </div>
  //     ) 
  //   } else if (avail.length === 1 && avail[0] === "users-friend") {
  //     return (
  //       <div className = "button-div">
  //         <DisplayButtonF />
  //       </div>
  //     ) 
  //   }else {
  //       return (
  //         <div>
  //           <p>Please fill out a questionnaire to get matched.</p>
  //         </div>
  //       )
  //     }
  //   }
  

  //   const [buttonShow, setButtonShow] = useState(<DisplayButtons />)
  //   setButtonShow(<DisplayButtons />)





   
    const [avail, setAvail] = useState([""])


    let fullURL = "http://localhost:9000/getFilledOutQs?user-key=" + mainuseremail.split("@")[0]
    fetch(fullURL)
    .then((response) => response.json())
    .then((response) => {
      setAvail(response);
      console.log(avail);
      console.log(avail.includes("users-date"))

    });


    
  

   let DisplayDataStudy = rowsStudy.map((row)=> (
    <TableRow
    key={row.name}
    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
    >
      {/* <TableCell >{row.matchType}</TableCell> */}
      {/* <TableCell >{row.name}</TableCell> */}
      <TableCell align="right">{row.pronouns}</TableCell>
      <TableCell align="right">{row.email}</TableCell>
    </TableRow>
   ));

   let DisplayDataFriend = rowsFriend.map((row)=> (
    <TableRow
    key={row.name}
    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
    >
      {/* <TableCell >{row.matchType}</TableCell> */}
      {/* <TableCell >{row.name}</TableCell> */}
      <TableCell align="right">{row.pronouns}</TableCell>
      <TableCell align="right">{row.email}</TableCell>
    </TableRow>
   ));

   let DisplayDataDate = rowsDate.map((row)=> (
    <TableRow
    key={row.name}
    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
    >
      {/* <TableCell >{row.matchType}</TableCell> */}
      {/* <TableCell >{row.name}</TableCell> */}
      <TableCell align="right">{row.pronouns}</TableCell>
      <TableCell align="right">{row.email}</TableCell>
    </TableRow>
   ));

  
  
   const [disDataS, setDisDataS] = useState(DisplayDataStudy)
   const [disDataF, setDisDataF] = useState(DisplayDataFriend)
   const [disDataD, setDisDataD] = useState(DisplayDataDate)




   function handleSubmitDate() {
    if (avail.includes("users-date")){
      
      let fullURL = "http://localhost:9000/getMatches?user-key=" + mainuseremail.split("@")[0] + "&Qtype=users-date"
      fetch(fullURL)
        .then((response) => response.json())
        .then((response) => {
          alert(response);

          console.log(mainuseremail)
          for (let i = 0; i < response.length; i++){
            // rows.push(createData(response[i].questionnaireType, response[i].name, response[i].pronouns, response[i].email))
            rowsDate.push(createData(response[i].name, response[i].pronouns, response[i].email))
          }
          
          setDisDataD(rowsDate.map((row) => (
            <TableRow
              key={row.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              {/* <TableCell >{row.matchType}</TableCell> */}
              <TableCell >{row.name}</TableCell>
              <TableCell align="right">{row.pronouns}</TableCell>
              <TableCell align="right">{row.email}</TableCell>
            </TableRow>
          )))
          }
          )} else {
            alert("You haven't filled out the date questionnaire. Please do so by naviagting to the New Matches page.")
          }
        
        }

    function handleSubmitFriend() {
      if (avail.includes("users-friend")){
          let fullURL = "http://localhost:9000/getMatches?user-key=" + mainuseremail.split("@")[0] + "&Qtype=users-friend"
          fetch(fullURL)
            .then((response) => response.json())
            .then((response) => {
              alert(response);
              console.log(mainuseremail)
              for (let i = 0; i < response.length; i++){
                // rows.push(createData(response[i].questionnaireType, response[i].name, response[i].pronouns, response[i].email))
                rowsFriend.push(createData(response[i].name, response[i].pronouns, response[i].email))
              }
              
              setDisDataF(rowsFriend.map((row) => (
                <TableRow
                  key={row.name}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  {/* <TableCell >{row.matchType}</TableCell> */}
                  <TableCell >{row.name}</TableCell>
                  <TableCell align="right">{row.pronouns}</TableCell>
                  <TableCell align="right">{row.email}</TableCell>
                </TableRow>
              )))
              }
              )} else {
              alert("You haven't filled out the friend questionnaire. Please do so by naviagting to the New Matches page.")
            }
            
            }



    function handleSubmitStudy() {
      if (avail.includes("users-study")){
        let fullURL = "http://localhost:9000/getMatches?user-key=" + mainuseremail.split("@")[0] + "&Qtype=users-study"
        fetch(fullURL)
          .then((response) => response.json())
          .then((response) => {
            alert(response);
            console.log(mainuseremail)
            for (let i = 0; i < response.length; i++){
              // rows.push(createData(response[i].questionnaireType, response[i].name, response[i].pronouns, response[i].email))
              rowsStudy.push(createData(response[i].name, response[i].pronouns, response[i].email))

            }
            
            setDisDataS(rowsStudy.map((row) => (
              <TableRow
                key={row.name}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                {/* <TableCell > {row.matchType}</TableCell> */}
                <TableCell >{row.name}</TableCell>
                <TableCell align="right">{row.pronouns}</TableCell>
                <TableCell align="right">{row.email}</TableCell>
              </TableRow>
            )))
            })} else {
          alert("You haven't filled out the study questionnaire. Please do so by naviagting to the New Matches page.")
        }
      }
        
  

  return (

    <div>
    
      <div className = "button-div">
        <Button id ="button-style2" variant="outlined" onClick={handleSubmitDate}>
            <label htmlFor="usernameInput">Click for DATE matches</label>
        </Button>
              
        <Button id ="button-style2" variant="outlined"onClick={handleSubmitFriend}>
            <label htmlFor="usernameInput">Click for FRIEND matches</label>
        </Button>

        <Button id ="button-style2" variant="outlined" onClick={handleSubmitStudy}>
            <label htmlFor="usernameInput">Click for STUDY matches</label>
        </Button>
      </div>

  
      <h1>Study Matches</h1>
      <TableContainer component={Paper} id="t-cont">
          <Table id="table" size="small" aria-label="a dense table">
            <TableHead >
              <TableRow className="top-row">
                {/* <TableCell>Match Type</TableCell> */}
                <TableCell>Name</TableCell>
                <TableCell align="right">Pronouns</TableCell>
                <TableCell align="right">Email</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
            

              {disDataS}

            </TableBody>
          </Table>
      </TableContainer>

      <h1>Friend Matches</h1>
      <TableContainer component={Paper} id="t-cont">
          <Table id="table" size="small" aria-label="a dense table">
            <TableHead >
              <TableRow className="top-row">
                {/* <TableCell>Match Type</TableCell> */}
                <TableCell>Name</TableCell>
                <TableCell align="right">Pronouns</TableCell>
                <TableCell align="right">Email</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
            

              {disDataF}

            </TableBody>
          </Table>
      </TableContainer>

      <h1>Date Matches</h1>
      <TableContainer component={Paper} id="t-cont">
          <Table id="table" size="small" aria-label="a dense table">
            <TableHead >
              <TableRow className="top-row">
                {/* <TableCell>Match Type</TableCell> */}
                <TableCell>Name</TableCell>
                <TableCell align="right">Pronouns</TableCell>
                <TableCell align="right">Email</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
            

              {disDataD}

            </TableBody>
          </Table>
      </TableContainer>

      

    </div>
    
    )
}

export default Profile;