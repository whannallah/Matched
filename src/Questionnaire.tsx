import React from 'react';
import Button from '@mui/material/Button';
import './App.css';
import { Formik, useFormik } from 'formik';
import * as Yup from 'yup';



// inspired from https://codesandbox.io/s/formik-v2-tutorial-final-ge1pt?file=/src/index.js
// and https://formik.org/docs/tutorial

// Questionnaire PAGE

// const validate = values => {
//     const errors = {};
//     if (!values.name) {
//       errors.name = 'Required';
//     } else if (values.name.length > 15) {
//       errors.name = 'Must be 15 characters or less';
//     }
  
//     if (!values.pronouns) {
//       errors.pronouns = 'Required';
//     } else if (values.pronouns.length > 10) {
//       errors.pronouns = 'Must be 10 characters or less';
//     }

//     if (!values.classYear) {
//         errors.classYear = 'Required';
//       } else if (values.classYear.length > 4) {
//         errors.classYear = 'Invalid class year';
//       }
  
//     if (!values.email) {
//       errors.email = 'Required';
//     } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.email)) {
//       errors.email = 'Invalid email address';
//     }
  
//     return errors;
//   };

const Questionnaire = () => {
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
      perfSat: '',
      dreamVac: '',
      hobby: '',
      reasoning: ''
    },
    //validate,
    onSubmit: values => {
      alert(JSON.stringify(values, null, 2));
      // send data to backend
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
        value={formik.values.name}
      />

        {formik.errors.name ? <div>{formik.errors.name}</div> : null}

      <label className="labelForm" htmlFor="pronouns">Pronouns: </label>
      <input
        id="pronouns"
        name="pronouns"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.pronouns}
      />


        {formik.errors.pronouns ? <div>{formik.errors.pronouns}</div> : null}


      <label className="labelForm" htmlFor="classYear">Class year: </label>
      <input
        id="classYear"
        name="classYear"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.classYear}
      />

        {formik.errors.classYear ? <div>{formik.errors.classYear}</div> : null}


      <label className="labelForm" htmlFor="email">Email: </label>
      <input
        id="email"
        name="email"
        type="email"
        onChange={formik.handleChange}
        value={formik.values.email}
      />

        {formik.errors.email ? <div>{formik.errors.email}</div> : null}

      <label className="labelForm" htmlFor="perfSat">Describe your perfect Saturday at Brown: </label>
      <input
        id="perfSat"
        name="perfSat"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.perfSat}
      />

      <label className="labelForm" htmlFor="dreamVac">Describe your dream vacation: </label>
      <input
        id="dreamVac"
        name="dreamVac"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.dreamVac}
      />

      <label className="labelForm" htmlFor="hobby">Talk about something you enjoy that you wish you had more time to do. (This could be a sport, hobby, or any sort of activity) </label>
      <input
        id="hobby"
        name="hobby"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.hobby}
      />

      <label className="labelForm" htmlFor="reasoning">What is your reasoning for filling out this questionnaire? </label>
      <input
        id="reasoning"
        name="reasoning"
        type="text"
        onChange={formik.handleChange}
        value={formik.values.reasoning}
      />

      <button className="labelForm" type="submit">Submit</button>
    </form>
  );
};



export default Questionnaire;