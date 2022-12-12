import React,{ useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import Login from './Login';
import emailID from "./Login"

import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getDatabase, ref, set, onValue } from "firebase/database"
import { createNull, isFunctionDeclaration, isNonNullExpression } from 'typescript';
import { Button } from '@mui/material';

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
    createData('Study Buddy', 'Sam', 'she/her', 'samantha_shulman@brown.edu'),
    // createData('Date',  'Emily', 'she/her', 'emily_perelman@brown.edu'),
    // createData('Friend',  'Whitney', 'she/her', 'N/A'),
    // createData('Date',  'Kam', 'he/him', 'N/A')
   ];



  // useEffect(() => {
  //   // Update the data with a listener onto realtime database
  //   const matchesRef = ref(db, 'users/' + 45604 + '/email');
    // onValue(matchesRef, (snapshot) => {
  //     // parse snapshot
  //     // use setMatches to update in UI
  //   });
  // });


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


   const [matches, setMatches] = useState(null);


   const handleSubmit = () => {
      fetch ('http://localhost:9000/getMatches?user-key=samantha_shulman&Qtype=users-date')
        .then((response) => response.json())
        .then((response) => {
          setMatches(response.status);
        }) 

      alert("hii")

      // DisplayData = rows.map((row)=> (
      //   <TableRow
      //   key={row.matchType}
      //   sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
      //   >
      //     <TableCell >
      //     {row.matchType}
      //     </TableCell>
      //     <TableCell >{row.name}</TableCell>
      //     <TableCell align="right">{row.pronouns}</TableCell>
      //     <TableCell align="right">{row.email}</TableCell>
      //   </TableRow>
      // )
      // )

    }



  return (

    <div>
    {/* <Button onClick={() => setMatches(DisplayData2)}> */}
    <Button onClick={() => handleSubmit}>
        <label htmlFor="usernameInput">Click for matches</label>
    </Button>

    <TableContainer component={Paper}>
        <Table id="table" sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow className="top-row">
              <TableCell>Match Type</TableCell>
              <TableCell>Name</TableCell>
              <TableCell align="right">Pronouns</TableCell>
              <TableCell align="right">Email</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            
            {rows.map((row) => (
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
            ))}

          </TableBody>
        </Table>
    </TableContainer>
    </div>
    
    )
}

export default Profile;