package com.akumasoft.mapper;

import java.util.List;
import java.util.UUID;

import com.akumasoft.dto.Plantilla.CreatePlantilla.CreatePlantillaRq;
import com.akumasoft.dto.Plantilla.CreatePlantilla.CreateValores;
import com.akumasoft.model.Emails.Plantillas;
import com.akumasoft.model.Emails.Valores;

public class PlantillaMapper {
    public Plantillas toPlantilla(CreatePlantillaRq request, UUID clienteId) {
        if (request == null) {
            return null;
        }
       Plantillas plantilla = new Plantillas();
       plantilla.setNombre(request.nombre());
       plantilla.setCodigo(request.codigo());
       plantilla.setArchivo(request.archivo());
       plantilla.setClienteId(clienteId);
       return plantilla;
    }

    public List<Valores> toValoresList(List<CreateValores> request, UUID plantillaId) {
        return request.stream()
            .map(valor -> toValores(valor, plantillaId))
            .toList();
    }

    public Valores toValores(CreateValores request, UUID plantillaId) {
        Valores valor = new Valores();
        valor.setClave(request.clave());
        valor.setDescripcion(request.descripcion());
        valor.setEsObligatorio(request.requerido());
        valor.setPlantillaId(plantillaId);
        return valor;
    }
}
