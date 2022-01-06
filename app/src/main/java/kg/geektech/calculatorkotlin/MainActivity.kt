package kg.geektech.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kg.geektech.calculatorkotlin.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.btn0.setOnClickListener { setTextFields("0") }
        binding.btn1.setOnClickListener { setTextFields("1") }
        binding.btn2.setOnClickListener { setTextFields("2") }
        binding.btn3.setOnClickListener { setTextFields("3") }
        binding.btn4.setOnClickListener { setTextFields("4") }
        binding.btn5.setOnClickListener { setTextFields("5") }
        binding.btn6.setOnClickListener { setTextFields("6") }
        binding.btn7.setOnClickListener { setTextFields("7") }
        binding.btn8.setOnClickListener { setTextFields("8") }
        binding.btn9.setOnClickListener { setTextFields("9") }
        binding.btnVychetanie.setOnClickListener { setTextFields("-") }
        binding.btnSlojenie.setOnClickListener { setTextFields("+") }
        binding.btnUmnojenie.setOnClickListener { setTextFields("*") }
        binding.btnDelenie.setOnClickListener { setTextFields("/") }
        binding.btnScob1.setOnClickListener { setTextFields("(") }
        binding.btnScob2.setOnClickListener { setTextFields(")") }
        binding.btnTochka.setOnClickListener { setTextFields(".") }
        binding.btnAc.setOnClickListener {
            binding.tvMathOperation.text = ""
            binding.tvResult.text = ""
        }
        binding.btnBack.setOnClickListener {
            val str = binding.tvMathOperation.text.toString()
            if (str.isNotEmpty()) {
                binding.tvMathOperation.text = str.substring(0, str.length - 1)
            }
            binding.tvResult.text = ""
        }

        binding.btnRavno.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.tvMathOperation.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()

                if (result == longRes.toDouble())
                    binding.tvResult.text = longRes.toString()
                else
                    binding.tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Aziz", "initListeners: ${e.localizedMessage}")
            }
        }
    }

    private fun setTextFields(str: String) {
        if (binding.tvResult.text != ""){
            binding.tvMathOperation.text = binding.tvResult.text
            binding.tvResult.text = ""
        }
        binding.tvMathOperation.append(str)
    }
}