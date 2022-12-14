import React,{ useState, useEffect } from 'react';
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
    matchType: string,
    name: string,
    pronouns: string,
    email: string
  ) {
    return { matchType, name, pronouns, email };
  }


   const rows = [
    createData('', '', '', ''),
    // createData('Date',  'Emily', 'she/her', 'emily_perelman@brown.edu'),
    // createData('Friend',  'Whitney', 'she/her', 'N/A'),
    // createData('Date',  'Kam', 'he/him', 'N/A')
   ];




   let DisplayData = rows.map((row)=> (
    <TableRow
    key={row.matchType}
    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
    >
      <TableCell >
      {row.matchType}
      </TableCell>
      <TableCell >{row.name}</TableCell>
      <TableCell align="right">{row.pronouns}</TableCell>
      <TableCell align="right">{row.email}</TableCell>
    </TableRow>
   ));

   const [disData, setDisData] = useState(DisplayData)


   function handleSubmitDate() {
      let fullURL = "http://localhost:9000/getMatches?user-key=" + mainuseremail.split("@")[0] + "&Qtype=users-date"
      fetch(fullURL)
        .then((response) => response.json())
        .then((response) => {
          alert(response);
          console.log(response[0])
        
          console.log(mainuseremail)
          
          console.log(response.length);
          console.log(rows)
          for (let i = 0; i < response.length; i++){
            rows.push(createData(response[i].questionnaireType, response[i].name, response[i].pronouns, response[i].email))
          }
          
          setDisData(rows.map((row) => (
            <TableRow
              key={row.matchType}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell >
                {row.matchType}
              </TableCell>
              <TableCell >{row.name}</TableCell>
              <TableCell align="right">{row.pronouns}</TableCell>
              <TableCell align="right">{row.email}</TableCell>
            </TableRow>
          )))
          }
          )}

    function handleSubmitFriend() {
          // fetch('http://localhost:9000/getMatches?user-key=" + emailID + "&Qtype=users-date')
          let fullURL = "http://localhost:9000/getMatches?user-key=" + mainuseremail.split("@")[0] + "&Qtype=users-friend"
          fetch(fullURL)
            .then((response) => response.json())
            .then((response) => {
              alert(response);
              // console.log(response[0])
              console.log(emailID)
              // console.log(response.length);
              // console.log(rows)
              for (let i = 0; i < response.length; i++){
                rows.push(createData(response[i].questionnaireType, response[i].name, response[i].pronouns, response[i].email))
              }
              
              setDisData(rows.map((row) => (
                <TableRow
                  key={row.matchType}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell >
                    {row.matchType}
                  </TableCell>
                  <TableCell >{row.name}</TableCell>
                  <TableCell align="right">{row.pronouns}</TableCell>
                  <TableCell align="right">{row.email}</TableCell>
                </TableRow>
              )))
              }
              )}

    function handleSubmitStudy() {
          //console.log(mainuseremail.split("@")[0])
          let fullURL = "http://localhost:9000/getMatches?user-key=" + mainuseremail.split("@")[0] + "&Qtype=users-study"
            fetch(fullURL)
              .then((response) => response.json())
              .then((response) => {
                alert(response);
                console.log(response[0])
                console.log(emailID)
                console.log(response.length);
                console.log(rows)
                for (let i = 0; i < response.length; i++){
                  rows.push(createData(response[i].questionnaireType, response[i].name, response[i].pronouns, response[i].email))
                }
                
                setDisData(rows.map((row) => (
                  <TableRow
                    key={row.matchType}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                  >
                    <TableCell >
                      {row.matchType}
                    </TableCell>
                    <TableCell >{row.name}</TableCell>
                    <TableCell align="right">{row.pronouns}</TableCell>
                    <TableCell align="right">{row.email}</TableCell>
                  </TableRow>
                )))
                }
                )}


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

    <TableContainer component={Paper}>
        <Table id="table" size="small" aria-label="a dense table">
          <TableHead >
            <TableRow className="top-row">
              <TableCell>Match Type</TableCell>
              <TableCell>Name</TableCell>
              <TableCell align="right">Pronouns</TableCell>
              <TableCell align="right">Email</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
           

            {disData}

          </TableBody>
        </Table>
    </TableContainer>
    </div>
    
    )
}

export default Profile;