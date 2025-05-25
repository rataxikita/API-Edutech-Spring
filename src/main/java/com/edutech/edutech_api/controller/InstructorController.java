//Catalina Rosales->rataxikita
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


import com.edutech.edutech_api.model.Instructor;
import com.edutech.edutech_api.model.Pregunta;
import com.edutech.edutech_api.model.Respuesta;
import com.edutech.edutech_api.repository.InstructorRepository;
import com.edutech.edutech_api.repository.PreguntaRepository;
import com.edutech.edutech_api.repository.RespuestaRepository;

@RestController
@RequestMapping("/instructores")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private PreguntaRepository preguntaRepo;

    @Autowired
    private RespuestaRepository respuestaRepo;

    // CRUD básico

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Instructor i) {
    // Validar RUT
    Instructor existePorRut = instructorRepo.findByRut(i.getRut());
    if (existePorRut != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("RUT ya registrado");
    }

    // Validar correo
    Instructor existePorCorreo = instructorRepo.findByCorreo(i.getCorreo(), null);
    if (existePorCorreo != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Correo ya registrado");
    }

    if (i.getEstado() == null) {
        i.setEstado("activo");
    }

    return ResponseEntity.ok(instructorRepo.save(i));
}

    @GetMapping
    public List<Instructor> listar() {
        return instructorRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Instructor i = instructorRepo.findById(id).orElse(null);
        if (i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(i);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Instructor datos) {
        Instructor i = instructorRepo.findById(id).orElse(null);
        if (i == null) return ResponseEntity.notFound().build();

        i.setNombre(datos.getNombre());
        i.setApellido(datos.getApellido());
        i.setEstado(datos.getEstado());
        return ResponseEntity.ok(instructorRepo.save(i));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!instructorRepo.existsById(id)) return ResponseEntity.notFound().build();

        instructorRepo.deleteById(id);
        return ResponseEntity.ok("Instructor eliminado");
    }

    // Ver cursos que dicta
    @GetMapping("/{id}/cursos")
    public ResponseEntity<?> cursosDelInstructor(@PathVariable Long id) {
        List<Curso> cursos = cursoRepo.findAll();
        List<Curso> cursosInstructor = new ArrayList<>();

        for (Curso c : cursos) {
            if (c.getInstructor().getId().equals(id)) {
                cursosInstructor.add(c);
            }
        }

        return ResponseEntity.ok(cursosInstructor);
    }

    // Ver preguntas de estudiantes
    @GetMapping("/{id}/preguntas")
    public ResponseEntity<?> verPreguntas(@PathVariable Long id) {
        List<Pregunta> preguntas = preguntaRepo.findAll();
        List<Pregunta> preguntasInstructor = new ArrayList<>();

        for (Pregunta p : preguntas) {
            if (p.getCurso().getInstructor().getId().equals(id)) {
                preguntasInstructor.add(p);
            }
        }

        return ResponseEntity.ok(preguntasInstructor);
    }

    // Responder preguntas
    @PostMapping("/{id}/responder")
    public ResponseEntity<?> responder(@PathVariable Long id, @RequestBody Respuesta r) {
        if (!r.getPregunta().getCurso().getInstructor().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No puedes responder esta pregunta");
        }
        return ResponseEntity.ok(respuestaRepo.save(r));
    }
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
    String correo = datos.get("correo");
    String clave = datos.get("clave");

    if (correo == null || clave == null) {
        return ResponseEntity.badRequest().body("Faltan datos de acceso");
    }

    Instructor instructor = instructorRepo.findByCorreoAndClave(correo, clave);

    if (instructor == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    if (!"activo".equalsIgnoreCase(instructor.getEstado())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Instructor inactivo");
    }

    return ResponseEntity.ok(instructor);
}

//Catalina Rosales->rataxikita