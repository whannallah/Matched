import React, { useState } from 'react';
import Button from '@mui/material/Button';
import './App.css';
import { Formik, useFormik } from 'formik';
import * as Yup from 'yup';
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';
import { mainuseremail } from './Login';



// inspired from https://codesandbox.io/s/formik-v2-tutorial-final-ge1pt?file=/src/index.js
// and https://formik.org/docs/tutorial

// Friend Questionnaire PAGE




const QuestionnaireF = () => {

  const [zip, setZip] = useState("");
 
  const formik = useFormik({
    initialValues: {
      name: '',
      pronouns: '',
      classYear: '',
      email: '',
      perfSat: '',
      dreamVac: '',
      hobby: '',
      reasoning: ''
    },
    validationSchema: Yup.object({
        name: Yup.string()
          .max(15, "Must be 15 characters or less")
          .required("Required"),
        pronouns: Yup.string()
          .max(15, "Must be 15 characters or less")
          .required("Required"),
        classYear: Yup.string()
          .max(4, "Must be a valid class year")
          .min(4, "Must be a valid class year")
          .required("Required"),
        perfSat: Yup.string()
          .max(5000, "Must be 100 character or less")
          .required("Required"),
        dreamVac: Yup.string()
          .max(5000, "Must be 100 character or less")
          .required("Required"),
        hobby: Yup.string()
          .max(5000, "Must be 100 character or less")
          .required("Required"),
        reasoning: Yup.string()
          .max(5000, "Must be 100 character or less")
          .required("Required"),
      }),
    onSubmit: values => {
        values.email = mainuseremail;
        const surveyData = JSON.stringify(values, null, 2);
        alert(surveyData)

    // send data to backend

        fetch('http://localhost:9000/getQuestionairreResponse?data-vals=' + surveyData + '&Qtype=friend')
     
    },
  });
  
  return (
    <div id="form-module">
    <form style={{margin: 100, padding: 50, position: "relative" }} onSubmit={formik.handleSubmit}>
      <label className="labelForm" htmlFor="name">Name: </label>
      <input
        id="name"
        name="name"
        type="text"
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
        value={formik.values.name}
      />

    {formik.touched.name && formik.errors.name ? (
         <div>{formik.errors.name}</div>
       ) : null}

      <label className="labelForm" htmlFor="pronouns">Pronouns: </label>
      <input
        id="pronouns"
        name="pronouns"
        type="text"
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
        value={formik.values.pronouns}
      />

        {formik.touched.pronouns && formik.errors.pronouns ? (
         <div>{formik.errors.pronouns}</div>
       ) : null}


      <label className="labelForm" htmlFor="classYear">Class year: </label>
      <input
        id="classYear"
        name="classYear"
        type="text"
        onChange={formik.handleChange}
        onBlur={formik.handleBlur}
        value={formik.values.classYear}
      />

        {formik.touched.classYear && formik.errors.classYear ? (
         <div>{formik.errors.classYear}</div>
       ) : null}


      <label className="labelForm" htmlFor="perfSat">Describe your perfect Saturday at Brown: </label>
      <input
        id="perfSat"
        name="perfSat"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.perfSat}
      />

        {formik.touched.perfSat && formik.errors.perfSat ? (
         <div>{formik.errors.perfSat}</div>
       ) : null}

      <label className="labelForm" htmlFor="dreamVac">Describe your dream vacation: </label>
      <input
        id="dreamVac"
        name="dreamVac"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.dreamVac}
      />

        {formik.touched.dreamVac && formik.errors.dreamVac ? (
         <div>{formik.errors.dreamVac}</div>
       ) : null}

      <label className="labelForm" htmlFor="hobby">Talk about something you enjoy that you wish you had more time to do. (This could be a sport, hobby, or any sort of activity) </label>
      <input
        id="hobby"
        name="hobby"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.hobby}
      />

        {formik.touched.hobby && formik.errors.hobby ? (
         <div>{formik.errors.hobby}</div>
       ) : null}

      <label className="labelForm" htmlFor="reasoning">What is your reasoning for filling out this questionnaire? </label>
      <input
        id="reasoning"
        name="reasoning"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.reasoning}
      />

        {formik.touched.reasoning && formik.errors.reasoning ? (
         <div>{formik.errors.reasoning}</div>
       ) : null}

      {/* <div>

      <FormControlLabel
        name="acceptance"
        value="start"
        control={<Checkbox />}
        label="I consent to my data being used to find a match"
        labelPlacement="end"
      />
      </div> */}

    <p>By submitting this form, I consent to the analysis and storage of my data, as well as the sharing of my name, pronouns, and email with my
    matches.</p>

      <button className="labelForm" type="submit">Submit</button>
    </form>
    </div>
  );
};



export default QuestionnaireF;