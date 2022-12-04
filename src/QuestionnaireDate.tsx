import React, { useState } from 'react';
import Button from '@mui/material/Button';
import './App.css';
import { Formik, useFormik } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';



// inspired from https://codesandbox.io/s/formik-v2-tutorial-final-ge1pt?file=/src/index.js
// and https://formik.org/docs/tutorial

// Date Questionnaire PAGE




const QuestionnaireD = () => {

  const [zip, setZip] = useState("");
  // Note that we have to initialize ALL of fields with values. These
  // could come from props, but since we don’t want to prefill this form,
  // we just use an empty string. If we don’t do this, React will yell
  // at us.
  const formik = useFormik({
    initialValues: {
      name: '',
      pronouns: '',
      classYear: '',
      email: '',
      perfDate: '',
      expectations: '',
      passions: '',
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
        email: Yup.string()
          .email('Invalid email address')
          .required('Required'),
        perfDate: Yup.string()
          .max(100, "Must be 100 character or less")
          .required("Required"),
        expectations: Yup.string()
          .max(100, "Must be 100 character or less")
          .required("Required"),
        passions: Yup.string()
          .max(100, "Must be 100 character or less")
          .required("Required"),
        reasoning: Yup.string()
          .max(100, "Must be 100 character or less")
          .required("Required"),
      }),
    onSubmit: values => {
    
        const surveyData = JSON.stringify(values, null, 2);
        alert(surveyData)

    // send data to backend

        fetch('http://localhost:9000/getQuestionairreResponse?data-vals=' + surveyData + '&Qtype=date')
     
    },
  });
  
  return (
    <form style ={{margin: 200, fontSize: 28, fontFamily: "Georgia", position: "relative"}} onSubmit={formik.handleSubmit}>
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


      <label className="labelForm" htmlFor="email">Email: </label>
      <input
        id="email"
        name="email"
        type="email"
        onChange={formik.handleChange}
        value={formik.values.email}
      />

      {formik.touched.email && formik.errors.email ? (
         <div>{formik.errors.email}</div>
       ) : null}

      <label className="labelForm" htmlFor="perfDate">Describe your perfect date: </label>
      <input
        id="perfDate"
        name="perfDate"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.perfDate}
      />

        {formik.touched.perfDate && formik.errors.perfDate ? (
         <div>{formik.errors.perfDate}</div>
       ) : null}

      <label className="labelForm" htmlFor="expectations">Describe your expectations in a romantic relationship: </label>
      <input
        id="expectations"
        name="expectations"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.expectations}
      />

        {formik.touched.expectations && formik.errors.expectations ? (
         <div>{formik.errors.expectations}</div>
       ) : null}

      <label className="labelForm" htmlFor="passions">Talk about something you are passionate about:) </label>
      <input
        id="passions"
        name="passions"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.passions}
      />

        {formik.touched.passions && formik.errors.passions ? (
         <div>{formik.errors.passions}</div>
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

      <button className="labelForm" type="submit">Submit</button>
    </form>
  );
};



export default QuestionnaireD;