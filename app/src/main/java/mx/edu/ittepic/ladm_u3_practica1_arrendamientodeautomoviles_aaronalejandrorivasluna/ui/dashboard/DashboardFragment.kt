package mx.edu.ittepic.ladm_u3_practica1_arrendamientodeautomoviles_aaronalejandrorivasluna.ui.dashboard

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u3_practica1_arrendamientodeautomoviles_aaronalejandrorivasluna.Automovil
import mx.edu.ittepic.ladm_u3_practica1_arrendamientodeautomoviles_aaronalejandrorivasluna.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTituloDes
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btnInsertar.setOnClickListener {
            var automovil = Automovil(requireContext())

            automovil.marca= binding.marca.text.toString()
            automovil.modelo = binding.modelo.text.toString()
            automovil.kilometraje = binding.kilometraje.text.toString().toInt()

            val resultado = automovil.insertar()

            if (resultado){
                Toast.makeText(requireContext(), "SE HA INSERTADO CON EXITO", Toast.LENGTH_LONG).show()
                //mostrarDatosEnListView()
                binding.marca.setText("")
                binding.modelo.setText("")
                binding.kilometraje.setText("")
            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle("ERROR")
                    .setMessage("NO SE PUDO INSERTAR")
                    .show()
            }
        }

        binding.btnConsultar.setOnClickListener {
            var datosAutomovil = ArrayList<Automovil>()
            if (!binding.marca.text.isEmpty()){
                datosAutomovil = Automovil(requireContext()).mostrarAutomovilMarca(binding.marca.text.toString())
            }else if (!binding.modelo.text.isEmpty()){
                datosAutomovil  = Automovil(requireContext()).mostrarAutomovilModelo(binding.modelo.text.toString())
            }else if (!binding.kilometraje.text.isEmpty()){
                datosAutomovil = Automovil(requireContext()).mostrarAutomovilKilometraje(binding.kilometraje.text.toString())
            }
            var msj = ""

            if (datosAutomovil.size == 0){
                AlertDialog.Builder(requireContext())
                    .setTitle("ERROR")
                    .setMessage("NO EXISTEN DATOS!")
                    .show()
                return@setOnClickListener
            }

            (0..datosAutomovil.size-1).forEach {
                msj +=  "MARCA: " + datosAutomovil[it].marca +
                        "MODELO: " + datosAutomovil[it].modelo +
                        "KILOMETRAJE: " + datosAutomovil[it].kilometraje+ "\n"
            }

            AlertDialog.Builder(requireContext())
                .setTitle("CONSULTA REALIZADA")
                .setMessage(msj)
                .setNegativeButton("Eliminar"){d,i->

                }
                .show()

        }

        
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}