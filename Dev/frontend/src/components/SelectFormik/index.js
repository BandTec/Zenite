import React from "react";
import { useField } from "formik";
import {
  Container,
  Rotulo,
  StyledSelect,
  TextoAlerta,
  ErrorMessage,
} from "./styles";

export default function SelectField({
  label,
  isLoading,
  isMulti,
  helpText,
  disabled,
  options,
  ...props
}) {
  const [field, meta, helpers] = useField(props);
  const { setValue, setTouched, setError } = helpers;

  const [didFocus, setDidFocus] = React.useState(false);
  const showFeedback = !!didFocus || meta.touched;

  const handleFocus = () => setDidFocus(true);
  const handleTouched = () => setTouched(true);

  const setFieldProps = (selectedOption) => {
    setTouched(true);
    setError(undefined);
    setDidFocus(true);
    if (selectedOption != null) {
      const value = isMulti
        ? selectedOption.map((item) => item.value)
        : selectedOption.value;
      setValue(value);
    } else {
      const value = isMulti ? [] : undefined;
      setValue(value);
    }
  };

  const getValue = () => {
    if (options) {
      if (field.value !== undefined && field.value !== null) {
        return isMulti
          ? options.filter((option) => field.value.indexOf(option.value) >= 0)
          : options.find((option) => option.value === field.value);
      }
    } else {
      return isMulti ? [] : "";
    }
  };
  return (
    <Container>
      <Rotulo>
        {texto} {required && "*"}
      </Rotulo>
      {textoAlerta && <TextoAlerta>{textoAlerta}</TextoAlerta>}
      <StyledSelect
        isDisabled={disabled}
        classNamePrefix={"Select"}
        {...props}
        {...field}
        isMulti={isMulti}
        options={options}
        value={getValue()}
        isLoading={isLoading}
        onChange={(selectedOption) => setFieldProps(selectedOption)}
        onFocus={handleFocus}
        onBlur={handleFocus}
        onKeyDown={handleTouched}
        feedback={showFeedback ? !!meta.error : false}
        theme={(theme) => ({
          ...theme,
          colors: {
            ...theme.colors,
            primary25: colors.primaryLight,
            primary: colors.primary,
          },
        })}
        aria-labelledby={props.name}
        aria-describedby={`${props.name}-feedback`}
      />
      {showFeedback
        ? meta.error && (
            <div id={`${props.name}-feedback`} aria-live="polite">
              <ErrorMessage>{meta.error}</ErrorMessage>
            </div>
          )
        : null}
    </Container>
  );
}
