import React, { useState } from "react";
import { Form, Formik } from "formik";
import { useHistory} from "react-router-dom";
import {
  Container,
  CaixaHorizontal,
  CorpoPagina,
  FormContainer,
  Titulo,
  Subtitulo,
  Caixa,
} from "./styles";
import Button from "./Button";
import StatusPage from "src/components/StatusPage";

const MultiStepForm = ({ children, initialValues, onSubmit, titulo }) => {
  const [stepNumber, setStepNumber] = useState(0);
  const steps = React.Children.toArray(children);
  const [snapshot, setSnapshot] = useState(initialValues);

  const step = steps[stepNumber];
  const totalSteps = steps.length;
  const isLastStep = stepNumber === totalSteps - 1;
  const history = useHistory();
  const goBack = () => {
    history.goBack();
  }

  const next = (values) => {
    setSnapshot(values);
    setStepNumber(Math.min(stepNumber + 1, totalSteps - 1));
  };

  const previous = (values) => {
    setSnapshot(values);
    setStepNumber(Math.max(stepNumber - 1, 0));
  };

  const handleSubmit = async (values, bag) => {
    if (step.props.onSubmit) {
      await step.props.onSubmit(values, bag);
    }
    if (isLastStep) {
      return onSubmit(values, bag);
    } else {
      bag.setTouched({});
      next(values);
    }
  };

  return (
    <Container>
      <CorpoPagina>
        <CaixaHorizontal center={true}>
          <StatusPage
            ativo={stepNumber === 0}
            texto="Dados Pessoais"
            temProximoPasso={true}
          />

          <StatusPage
            ativo={stepNumber === 1}
            texto="Endereço"
            temProximoPasso={true}
          />

          <StatusPage
            ativo={isLastStep}
            texto="Dados de Acesso"
            temProximoPasso={false}
          />
        </CaixaHorizontal>

        <Formik
          initialValues={snapshot}
          onSubmit={handleSubmit}
          validationSchema={step.props.validationSchema}
        >
          {(formik) => (
            <Form>
              <FormContainer>
                <Button
                  onClick={() => step === 0 ? goBack() : previous(formik.values)}
                  type="button"
                  texto="Voltar"
                  ladoDireito={false}
                />

                <Caixa>
                  <Subtitulo>{titulo}</Subtitulo>
                  <Titulo>{step.props.titulo}</Titulo>

                  {step}
                </Caixa>

                <Button
                  type="submit"
                  disabled={formik.isSubmitting}
                  texto={isLastStep ? "Finalizar" : "Proximo"}
                  concluir={isLastStep}
                />
              </FormContainer>
            </Form>
          )}
        </Formik>
      </CorpoPagina>
    </Container>
  );
};

export default MultiStepForm;
