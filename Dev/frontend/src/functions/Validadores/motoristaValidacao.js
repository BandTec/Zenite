import * as yup from "yup";
import { validarCPF } from "./validators";

export const validationStep1 = yup.object({
  nome: yup.string().required(),
  cpf: yup
    .string()
    .required()
    .test("cpf-validation", "CPF inválido", (value) => validarCPF(value)),
  dataNascimento: yup.date().required(),
  numeroTelefone: yup.string().required(),
  cnh: yup.string().required(),
});

export const validationStep2 = yup.object({
  endereco: yup.object().shape({
    cep: yup.string().required(),
    logradouro: yup.string().required(),
    numero: yup.string().required(),
    complemento: yup.string(),
    cidade: yup.string().required(),
    estado: yup.string().max(2).required(),
  }),
});

export const validationStep3 = yup.object({
  conta: yup.object().shape({
    email: yup.string().email().required(),
    senha: yup.string().required(),
  }),
  confirmarSenha: yup
    .string()
    .required()
    .test("passwords-match", "As senhas devem ser iguais", function (value) {
      return this.parent.conta.senha === value;
    }),
});
