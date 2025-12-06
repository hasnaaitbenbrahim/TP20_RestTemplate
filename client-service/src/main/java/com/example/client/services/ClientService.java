package com.example.client.services;

import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marque cette classe comme un composant Service
public class ClientService {

    @Autowired // Injection de dépendance
    private ClientRepository clientRepository;

    /**
     * Récupère tous les clients de la base de données
     * 
     * @return Liste de tous les clients
     */
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    /**
     * Récupère un client par son identifiant
     * 
     * @param id Identifiant du client
     * @return Le client correspondant
     * @throws Exception Si aucun client n'est trouvé avec cet ID
     */
    public Client findById(Long id) throws Exception {
        return clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Client non trouvé avec l'ID: " + id));
    }

    /**
     * Ajoute un nouveau client ou met à jour un client existant
     * 
     * @param client Le client à sauvegarder
     * @return Le client sauvegardé avec son ID généré
     */
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }
    
    /**
     * Met à jour un client existant
     * 
     * @param id L'identifiant du client à mettre à jour
     * @param clientDetails Les nouvelles informations du client
     * @return Le client mis à jour
     * @throws Exception Si aucun client n'est trouvé avec cet ID
     */
    public Client updateClient(Long id, Client clientDetails) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Client non trouvé avec l'ID: " + id));
        
        client.setNom(clientDetails.getNom());
        client.setAge(clientDetails.getAge());
        
        return clientRepository.save(client);
    }

    // Vous pouvez ajouter d'autres méthodes métier ici
}

