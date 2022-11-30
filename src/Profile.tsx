import React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

//MY MATCHES PAGE

function createData(
    matchType: string,
    name: string,
    pronouns: string,
    email: string
  ) {
    return { matchType, name, pronouns, email };
  }
  
  // going to be accessed from database 
  const rows = [
    createData('Study Buddy', 'Sam', 'she/her', 'samantha_shulman@brown.edu'),
    createData('Date',  'Emily', 'she/her', 'emily_perelman@brown.edu'),
    createData('Friend',  'Whitney', 'she/her', 'N/A'),
    createData('Date',  'Kam', 'he/him', 'N/A')
  ];

function Profile (){
    return (
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
                <TableCell component="th" scope="row">
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
    
    )
}

export default Profile;