import React, { useRef, useEffect } from "react";
import styled from "styled-components";
import { useField } from "@unform/core";

const StyledInput = styled.input`
  border: none;
  border-radius: 25px;
  padding: 10px 30px;
  width: 100%;
  text-align: center;
  font-size: 20px;
  `;

const Label = styled.label`
  color: black;
  font-size: 15pt;
  font-weight: 900;
  margin-top: 30px;
  width: 100%;
`;

export default function Input({
  label,
  name,
  inputfile,
  placeholder,
  ...rest
}) {
  const inputRef = useRef(null);
  const { fieldName, defaultValue, registerField, error } = useField(name);

  useEffect(() => {
    registerField({
      name: fieldName,
      ref: inputRef,
      getValue: (ref) => {
        return ref.current.value;
      },
      setValue: (ref, value) => {
        ref.current.value = value;
      },
      clearValue: (ref) => {
        ref.current.value = "";
      },
    });
  }, [fieldName, registerField]);

  return (
    <Label>
      {label}
      <StyledInput
        ref={inputRef}
        placeholder={placeholder}
        defaultValue={defaultValue}
        {...rest}
      />
    </Label>
  );
}
